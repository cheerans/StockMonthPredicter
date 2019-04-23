package com.stocks.dto;

public class HistoricalStockMonthData {

    private int month;
    private int year;
    private double gain;
    private StockRallyData rallyData;

    public HistoricalStockMonthData(int month, int year, double gain) {
        this.month=month;
        this.year=year;
        this.gain=gain;
    }

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public double getGain() {
		return gain;
	}

	public StockRallyData getRallyData() {
		return rallyData;
	}

	public void setRallyData(StockRallyData rallyData) {
		this.rallyData = rallyData;
	}
}