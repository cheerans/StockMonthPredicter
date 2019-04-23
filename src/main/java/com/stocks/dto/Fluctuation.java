package com.stocks.dto;

public class Fluctuation {

	private boolean high;
	private boolean	positive;
	private int year;
	private double gain;
	
	public Fluctuation(boolean high, boolean positive,int year, double gain) {
		
		super();
		this.high = high;
		this.positive = positive;
		this.year = year;
		this.gain = gain;
	}

	public boolean isHigh() {
		return high;
	}

	public boolean isPositive() {
		return positive;
	}

	public int getYear() {
		return year;
	}

	public double getGain() {
		return gain;
	}
}
