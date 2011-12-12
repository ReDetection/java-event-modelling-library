package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.EventTarget;
import ru.buglakov.study.term7.modelling.jpss.RandomUtils;
import ru.buglakov.study.term7.modelling.jpss.SimulationEvent;
import ru.buglakov.study.term7.modelling.jpss.Time;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.Transaction;
import ru.buglakov.study.term7.modelling.jpss.TransactionManager;

public class Generate implements EventTarget{

	private final BigInteger average;
	private final BigInteger difference;
	private TransactionInput next;
	private BigInteger count = null;

	public Generate(long average, long difference){
		this(new BigInteger(new Long(average).toString()),new BigInteger(new Long(difference).toString()));
	}
	
	public Generate(BigInteger average, BigInteger difference) {
		this.average = average;
		this.difference = difference;
		
	}
	
	public void start(BigInteger count){
		this.count  = count;
		TimeMachine.registerEvent(new SimulationEvent(next(),this));
	}
	
	@Override
	public void fire() {
		Transaction t = TransactionManager.createTransaction();
		t.sendTo(next);
		if(count!=null){
			count = count.subtract(BigInteger.ONE);
			if(count.equals(BigInteger.ZERO)){
				return;
			}
		}
		TimeMachine.registerEvent(new SimulationEvent(next(),this));
		
	}
	
	private Time next(){
		return new Time(TimeMachine.getTime().getTime().add(
							RandomUtils.normal(average, difference)
						));
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

	public TransactionInput getNext() {
		return next;
	}

	public void setNext(TransactionInput nextBlock) {
		if(this.next!=null){
			throw new RuntimeException("Переопределение следующего блока!");
		}
		this.next= nextBlock;
	}

}
