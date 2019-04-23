package com.stocks.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stocks.service.DerivedMarketStateImpl;
import com.stocks.service.MarketState;

public class DataHolder {
	
	private static DataHolder _instance = null;
	static {
		_instance = new DataHolder();
	}
	
	private Map<String, Map<String,List<HistoricalStockMonthData>>> monthYearGain = new HashMap<String, Map<String,List<HistoricalStockMonthData>>>();
	private Map<String, Map<String,List<HistoricalStockMonthData>>> balancedMonthYearGain = new HashMap<String, Map<String,List<HistoricalStockMonthData>>>();
	private Map<String, Map<String,Double>> yearGain = new HashMap<String, Map<String,Double>>();
	private Map<String,Double> bigYearGain = new HashMap<String,Double>();
	private RunConfig runConfig = null;
	private MarketState derivedMarketState = new DerivedMarketStateImpl();
	
	protected DataHolder() {
		
	}
	
	public static DataHolder getInstance() {
		return _instance;
	}

	public Map<String, Map<String, List<HistoricalStockMonthData>>> getMonthYearGain() {
		return getInstance().monthYearGain;
	}

	public void setMonthYearGain(Map<String, Map<String, List<HistoricalStockMonthData>>> monthYearGain) {
		getInstance().monthYearGain = monthYearGain;
	}

	public Map<String, Map<String, List<HistoricalStockMonthData>>> getBalancedMonthYearGain() {
		return getInstance().balancedMonthYearGain;
	}

	public void setBalancedMonthYearGain(
			Map<String, Map<String, List<HistoricalStockMonthData>>> balancedMonthYearGain) {
		getInstance().balancedMonthYearGain = balancedMonthYearGain;
	}

	public Map<String, Map<String, Double>> getYearGain() {
		return getInstance().yearGain;
	}

	public void setYearGain(Map<String, Map<String, Double>> yearGain) {
		getInstance().yearGain = yearGain;
	}

	public Map<String, Double> getBigYearGain() {
		return getInstance().bigYearGain;
	}

	public void setBigYearGain(Map<String, Double> bigYearGain) {
		getInstance().bigYearGain = bigYearGain;
	}

	public RunConfig getRunConfig() {
		return runConfig;
	}

	public void setRunConfig(RunConfig runConfig) {
		this.runConfig = runConfig;
	}

	public MarketState getDerivedMarketState() {
		return derivedMarketState;
	}

	public void setDerivedMarketState(MarketState derivedMarketState) {
		this.derivedMarketState = derivedMarketState;
	}
}
