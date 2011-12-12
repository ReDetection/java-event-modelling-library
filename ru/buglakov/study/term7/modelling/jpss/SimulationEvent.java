package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigInteger;

public abstract class SimulationEvent {
	
	private final Time fireAt;

	public SimulationEvent(BigInteger fireAfter) {
		this(new Time(fireAfter.add(TimeMachine.getTime().getTime())));
	}
	
	public SimulationEvent(Time fireAt){
		this.fireAt = fireAt;
		TimeMachine.registerEvent(this);
		
	}

	public Time getEventTime() {
		return fireAt;
	}
	
	public abstract void fire(); 
}
