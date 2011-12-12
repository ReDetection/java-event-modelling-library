package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.RandomUtils;
import ru.buglakov.study.term7.modelling.jpss.SimulationEvent;
import ru.buglakov.study.term7.modelling.jpss.Time;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;

public class Generate {

	private final BigInteger average;
	private final BigInteger difference;
	private BigInteger count = null;

	public Generate(BigInteger average, BigInteger difference) {
		this.average = average;
		this.difference = difference;
		
	}
	
	public void start(BigInteger count){
		this.count  = count;
		TimeMachine.registerEvent(new SimulationEvent(next()) {
			@Override
			public void fire() {
				
			}
		});
	}
	
	private Time next(){
		return new Time(RandomUtils.normal(average, difference));
	}

	public BigInteger getLeftCount() {
		return count;
	}

	public BigInteger getDifference() {
		return difference;
	}

	public BigInteger getAverage() {
		return average;
	}
}
