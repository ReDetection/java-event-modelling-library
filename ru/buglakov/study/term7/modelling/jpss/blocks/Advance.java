package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ru.buglakov.study.term7.modelling.jpss.EventTarget;
import ru.buglakov.study.term7.modelling.jpss.RandomUtils;
import ru.buglakov.study.term7.modelling.jpss.SimulationEvent;
import ru.buglakov.study.term7.modelling.jpss.Time;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.Transaction;

public class Advance implements TransactionInput,EventTarget{
	private final BigInteger average;
	private final BigInteger difference;
	private TransactionInput next;
	private List<Transaction> tr = new ArrayList<>(20); //TODO : может, убрать отсюда queue, сделать ссылку на транзакт в Eventе?
	
	public Advance(long average, long difference){
		this(new BigInteger(new Long(average).toString()),new BigInteger(new Long(difference).toString()));
	}
	
	public Advance(BigInteger average, BigInteger difference) {
		this.average = average;
		this.difference = difference;
		
	}

	@Override
	public void receive(Transaction transaction) {
		tr.add(transaction);
		TimeMachine.registerEvent(new SimulationEvent(next(),this));
	}

	@Override
	public void fire() {
		System.out.print('<');
		Transaction t = tr.get(0);
		tr.remove(0);
		t.sendTo(next);
		
	}
	
	private Time next(){
		return new Time(TimeMachine.getTime().getTime().add(
							RandomUtils.normal(average, difference)
						));
	}


	public TransactionInput getNext() {
		return next;
	}

	public void setNext(TransactionInput next) {
		if(this.next!=null){
			throw new RuntimeException("Переопределение следующего блока!");
		}
		this.next = next;
	}

}
