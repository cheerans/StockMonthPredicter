package com.stocks.app;

import com.stocks.dto.DataHolder;
import com.stocks.dto.RunConfig;
import com.stocks.service.StockDataReader;

public class Main {
    
  public static void main(String[] args) {
    
	  DataHolder.getInstance().setRunConfig(new RunConfig(01,19));
	  StockDataReader.readData();
  }
}