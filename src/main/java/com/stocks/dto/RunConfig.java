package com.stocks.dto;

public class RunConfig {

    private int month;
    private int year;
    
	public RunConfig(int month, int year) {
		super();
		this.month = month;
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
}
