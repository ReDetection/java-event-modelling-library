package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigInteger;

public class SimulationEvent{
	
	private final Time fireAt;
	private final EventTarget target;

	public SimulationEvent(BigInteger fireAfter, EventTarget target) {
		this(new Time(fireAfter.add(TimeMachine.getTime().getTime())),target);
	}
	
	public SimulationEvent(Time fireAt, EventTarget target){
		this.fireAt = fireAt;
		this.target = target;
		
	}

	public Time getEventTime() {
		return fireAt;
	}
	
	public void fire() {
		target.fire();
	}
	
}
