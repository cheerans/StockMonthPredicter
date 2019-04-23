package com.stocks.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.stock.constants.GeneralConstants;
import com.stocks.dto.DataHolder;
import com.stocks.dto.HistoricalStockMonthData;
import com.stocks.dto.StockRallyData;

public class StockDataReader {

	private static List<HistoricalStockMonthData> stockDataLst = new LinkedList<HistoricalStockMonthData>();

	public static void readData() {

		try {

			FileReader fr = new FileReader("monthlyData.csv");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {

				System.out.println(line);
				line = br.readLine();
				if ((null != line) && (false == line.isEmpty())) {

					String[] tokens = line.split(",");
					if (tokens.length >= 3) {
						stockDataLst.add(new HistoricalStockMonthData(Integer.parseInt(tokens[0]),
								Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2])));
					}
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {

		}
		createGainLossBatchDataForStocks();
	}

	private static void createGainLossBatchDataForStocks() {

		createYearData();
		createMonthYearData();
	}

	private static void createYearData() {

		Map<Integer, Double> yearGainLocal = new HashMap<Integer, Double>();
		stockDataLst.forEach((thisMonthYearData) -> {

			Integer year = thisMonthYearData.getYear();
			Double gain = 0.0;
			if (null == yearGainLocal.get(year)) {
				gain = 0.0;
			} else {
				gain = yearGainLocal.get(year);
			}
			yearGainLocal.put(year, gain + thisMonthYearData.getGain());
		});

		yearGainLocal.keySet().forEach((yearKeyVal) -> {

			String GAINKEY;
			if (yearGainLocal.get(yearKeyVal) >= 0.0) {
				GAINKEY = GeneralConstants.UP;
			} else {
				GAINKEY = GeneralConstants.DOWN;
			}

			if (null == DataHolder.getInstance().getYearGain().get(GAINKEY)) {
				DataHolder.getInstance().getYearGain().put(GAINKEY, new HashMap<String, Double>());
			}
			DataHolder.getInstance().getYearGain().get(GAINKEY).put(Integer.toString(yearKeyVal),
					yearGainLocal.get(yearKeyVal));
			if (Math.abs(yearGainLocal.get(yearKeyVal)) >= GeneralConstants.BIGFLUCTUATION) {

				DataHolder.getInstance().getBigYearGain().put(Integer.toString(yearKeyVal),
						yearGainLocal.get(yearKeyVal));
			}
		});
	}

	private static void createMonthYearData() {

		stockDataLst.forEach((thisMonthYearData) -> {

			String GAINKEY = "";
			StockRallyData rallyData = null;
			if (thisMonthYearData.getGain() >= 0) {

				if (false == GAINKEY.equals(GeneralConstants.UP)) {
					GAINKEY = GeneralConstants.UP;
					rallyData = new StockRallyData();
					rallyData.setStartMonthIndex(thisMonthYearData.getMonth());
				}
				rallyData.setSpikeMonths(rallyData.getSpikeMonths() + 1);
				rallyData.setNetGain(rallyData.getNetGain() + thisMonthYearData.getGain());
			} else {

				if (false == GAINKEY.equals(GeneralConstants.DOWN)) {
					GAINKEY = GeneralConstants.DOWN;
					rallyData = new StockRallyData();
					rallyData.setStartMonthIndex(thisMonthYearData.getMonth());
				}
				rallyData.setSpikeMonths(rallyData.getSpikeMonths() + 1);
				rallyData.setNetGain(rallyData.getNetGain() + thisMonthYearData.getGain());
			}

			// update the rally data
			thisMonthYearData.setRallyData(rallyData);

			String year = Integer.toString(thisMonthYearData.getYear());
			String monthYear = String.join(	
											Integer.toString(thisMonthYearData.getYear()),
											Integer.toString(thisMonthYearData.getMonth()), 
											GeneralConstants.DELIMITER
										  );

			List<HistoricalStockMonthData> thisMonthYearDataLst = null;
			if (null == DataHolder.getInstance().getMonthYearGain().get(GAINKEY)) {
				DataHolder.getInstance().getMonthYearGain().put(GAINKEY,
						new HashMap<String, List<HistoricalStockMonthData>>());
			}

			if (null == DataHolder.getInstance().getMonthYearGain().get(GAINKEY).get(String.join(monthYear))) {
				thisMonthYearDataLst = new ArrayList<HistoricalStockMonthData>();
				DataHolder.getInstance().getMonthYearGain().get(GAINKEY).put(monthYear, thisMonthYearDataLst);
			}

			if (null != DataHolder.getInstance().getBigYearGain().get(year)) {
				if (null == DataHolder.getInstance().getBalancedMonthYearGain().get(GAINKEY)) {
					DataHolder.getInstance().getBalancedMonthYearGain().put(GAINKEY,
							new HashMap<String, List<HistoricalStockMonthData>>());
				}
				DataHolder.getInstance().getBalancedMonthYearGain().get(GAINKEY).put(monthYear, thisMonthYearDataLst);
			}
			thisMonthYearDataLst.add(thisMonthYearData);
		});
	}
}
