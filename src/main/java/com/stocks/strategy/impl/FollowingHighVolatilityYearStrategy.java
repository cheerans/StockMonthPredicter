package com.stocks.strategy.impl;

import java.util.Map;

import com.stock.constants.GeneralConstants;
import com.stocks.dto.DataHolder;
import com.stocks.dto.Fluctuation;
import com.stocks.strategy.StockStrategy;

public class FollowingHighVolatilityYearStrategy implements StockStrategy{

	@Override
	public void executeRule() {
		
		Map<String, Double> bigGains = DataHolder.getInstance().getBigYearGain();
		
		int runYear = DataHolder.getInstance().getRunConfig().getYear();
		double gainLastYear = bigGains.get(runYear-1);
		double gainPreviousLastYear = bigGains.get(runYear-2);
		
		// A very very low return year is followed by a good return year
		// a very good return year is followed by a moderate year
		if(gainLastYear > Math.abs(GeneralConstants.BIGFLUCTUATION)){
			
			DataHolder.getInstance().
			getDerivedMarketState().
			getHighFluctuationlst().
			add(
					new Fluctuation(true, 
									gainLastYear > 0, 
									runYear-1, 
									gainLastYear
					   		  	   )		
			);
		}else if(gainPreviousLastYear > Math.abs(GeneralConstants.BIGFLUCTUATION)){
			   
			// Very High and very low reversal, Two very high			
			DataHolder.getInstance().
			getDerivedMarketState().
			getHighFluctuationlst().
			add(
					new Fluctuation(true, 
							   		gainPreviousLastYear > 0, 
							   		runYear-2, 
							   		gainPreviousLastYear
							   	   )		
			   );
			
			DataHolder.getInstance().
			getDerivedMarketState().
			getHighFluctuationlst().
			add(
					new Fluctuation(true, 
				   					gainLastYear > 0, 
				   					runYear-1, 
				   					gainLastYear
				   		  		   )		
			   );
		}
	}
}
