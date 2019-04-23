package com.stocks.strategy.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.stock.constants.GeneralConstants;
import com.stocks.dto.DataHolder;
import com.stocks.dto.HistoricalStockMonthData;
import com.stocks.dto.RunConfig;
import com.stocks.service.DerivedMarketStateImpl;
import com.stocks.strategy.StockStrategy;

public class FollowingVolatilityYearHistoricTrendBasedStrategy implements StockStrategy{

	@Override
	public void executeRule() {		
		
		
		// Volatility following year, can have special patterns for a month
		// Analyze those
		
		Map<String, Map<String, List<HistoricalStockMonthData>>> monthyearGain = DataHolder.getInstance().getBalancedMonthYearGain();		
		RunConfig config = DataHolder.getInstance().getRunConfig();
		
		String year = Integer.toString(config.getYear());
		String monthYear = String.join(	
										Integer.toString(config.getYear()),
										Integer.toString(config.getMonth()), 
										GeneralConstants.DELIMITER
									  );
		
		
		long upMonths = monthyearGain.get(GeneralConstants.UP)
									 .values()
								   	 .stream()
								   	 .flatMap(List::stream)
							         .collect(Collectors.toList())
							         .stream()
								   	 .filter(o -> o.getMonth() == (config.getMonth()))
								   	 .collect(Collectors.toSet())
								   	 .size();
		
		long downMonths = monthyearGain.get(GeneralConstants.DOWN)
									   .values()
									   .stream()
									   .flatMap(List::stream)
								       .collect(Collectors.toList())
								       .stream()
									   .filter(o -> o.getMonth() == (config.getMonth()))
									   .collect(Collectors.toSet())
									   .size();	
		
		((DerivedMarketStateImpl)DataHolder.getInstance().getDerivedMarketState()).setHistoricProbabilityForUP(((upMonths/(upMonths+downMonths)-0.5)/.5)*100);
				
	}		
}
