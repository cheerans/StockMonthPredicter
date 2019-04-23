package com.stocks.dto;

public class StockRallyData {

    private int spikeMonths;
    private int startMonthIndex;
    private double netGain;

    public StockRallyData(	) {

    }

	public int getSpikeMonths() {
		return spikeMonths;
	}

	public void setSpikeMonths(int spikeMonths) {
		this.spikeMonths = spikeMonths;
	}

	public int getStartMonthIndex() {
		return startMonthIndex;
	}

	public void setStartMonthIndex(int startMonthIndex) {
		this.startMonthIndex = startMonthIndex;
	}

	public double getNetGain() {
		return netGain;
	}

	public void setNetGain(double netGain) {
		this.netGain = netGain;
	}
}