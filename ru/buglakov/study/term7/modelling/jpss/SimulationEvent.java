package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigInteger;

public class SimulationEvent{
	
	private final Time fireAt;
	private final EventTarget target;
    private final Transaction linked;

	public SimulationEvent(BigInteger fireAfter, EventTarget target) {
		this(new Time(fireAfter),target,null);
	}
    
	public SimulationEvent(BigInteger fireAfter, EventTarget target,Transaction linked) {
		this(new Time(fireAfter),target,linked);
	}
	
	public SimulationEvent(Time fireAt, EventTarget target,Transaction linked){
		this.fireAt = fireAt;
		this.target = target;
        this.linked = linked;
		
	}
	public SimulationEvent(Time fireAt, EventTarget target){
		this.fireAt = fireAt;
		this.target = target;
        this.linked = null;
		
	}

	public Time getEventTime() {
		return fireAt;
	}
	
	public void fire() {
		target.fire(linked);
	}

    public Transaction getLinked() {
        return linked;
    }
	
}
