package com.stocks.service;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import com.stocks.dto.DataHolder;
import com.stocks.dto.Fluctuation;

public class DerivedMarketStateImpl implements MarketState{


	private List<Fluctuation> highFluctuationlst = new LinkedList<Fluctuation>();
	
	private double historicProbabilityForUP = 0.0;

	private int currentStreakLength;

	private Predicate<Fluctuation> highYearCondition = fluc -> fluc.isHigh() == true && fluc.isPositive();

	public boolean hasHighFluctuationEffect() {
		
		return highFluctuationlst.size() > 0;
	}

	public boolean isHighFluctuationPositive() {
		
		return highFluctuationlst.stream().anyMatch(highYearCondition);
	}
	
	public long highFluctuationCount() {
		
		return highFluctuationlst.stream().filter(highYearCondition).count();
	}
	
	public double highFluctuationNetAmount() {
		
		return highFluctuationlst.stream().mapToDouble(o -> o.getGain()).sum();
	}
	
	public boolean wasLastYearHighFluctuationYear() {
		
		 return highFluctuationlst.stream()
				.filter(highYearCondition)
				.filter(o -> o.getYear() == (DataHolder.getInstance().getRunConfig().getYear()-1)).count() > 0;
	}
	
	public List<Fluctuation> getHighFluctuationlst() {
		return highFluctuationlst;
	}

	public void setHighFluctuationlst(List<Fluctuation> highFluctuationlst) {
		this.highFluctuationlst = highFluctuationlst;
	}

	public double getHistoricProbabilityForUP() {
		return historicProbabilityForUP;
	}

	public void setHistoricProbabilityForUP(double historicProbabilityForUP) {
		this.historicProbabilityForUP = historicProbabilityForUP;
	}

}
