package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.EventTarget;
import ru.buglakov.study.term7.modelling.jpss.Utils;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.Transaction;
import ru.buglakov.study.term7.modelling.jpss.TransactionManager;

public class Generate extends TransactionOutput implements EventTarget{

	private final BigInteger average;
	private final BigInteger difference;
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
        TimeMachine.delay(Utils.normal(average, difference), this);
	}
	
	@Override
	public void fire(Transaction t_) {
		Transaction t = TransactionManager.createTransaction();
		t.sendTo(getNext());
		if(count!=null){
			count = count.subtract(BigInteger.ONE);
			if(count.equals(BigInteger.ZERO)){
				return;
			}
		}
        TimeMachine.delay(Utils.normal(average, difference), this);
		
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
