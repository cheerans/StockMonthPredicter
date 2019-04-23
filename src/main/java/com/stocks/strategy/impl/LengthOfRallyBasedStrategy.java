package com.stocks.strategy.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.stock.constants.GeneralConstants;
import com.stocks.dto.DataHolder;
import com.stocks.dto.HistoricalStockMonthData;
import com.stocks.dto.RunConfig;
import com.stocks.strategy.StockStrategy;

public class LengthOfRallyBasedStrategy implements StockStrategy{

	@Override
	public void executeRule() {
		
		//Two months before, to two months later, measure a streak length and vaergae length
		// and count of 2,3,4 month streaks
		
		Map<String, Map<String, List<HistoricalStockMonthData>>> monthyearGain = DataHolder.getInstance().getBalancedMonthYearGain();		
		RunConfig config = DataHolder.getInstance().getRunConfig();
		
		String year = Integer.toString(config.getYear());
		String monthYear = String.join(	
										Integer.toString(config.getYear()),
										Integer.toString(config.getMonth()), 
										GeneralConstants.DELIMITER
									  );
				
		for(int iMonth=1; iMonth < 12; iMonth++) {
			
			monthYear = String.join(	
									Integer.toString(config.getYear()),
									Integer.toString(config.getMonth()), 
									GeneralConstants.DELIMITER
								   );
			Optional<HistoricalStockMonthData> upMonths = monthyearGain.get(GeneralConstants.UP)
																  	   .get(monthyearGain)
																  	   .stream()
																  	   .filter(o -> o.getYear() == (config.getYear()))
																  	   .filter(o -> o.getYear() == (config.getMonth()))
																  	   .findFirst();
		}
		
		// A very very low return year is followed by a good return year
		// a very good return year is followed by a moderate year
	}
}
