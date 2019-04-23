package com.stocks.service;

import java.util.List;

import com.stocks.dto.Fluctuation;

public abstract interface MarketState {
	
	boolean hasHighFluctuationEffect();
	boolean isHighFluctuationPositive();
	long highFluctuationCount();
	double highFluctuationNetAmount();
	boolean wasLastYearHighFluctuationYear();
	List<Fluctuation> getHighFluctuationlst();
	double getHistoricProbabilityForUP();
}
