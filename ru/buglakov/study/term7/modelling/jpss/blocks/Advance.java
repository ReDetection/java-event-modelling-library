package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ru.buglakov.study.term7.modelling.jpss.EventTarget;
import ru.buglakov.study.term7.modelling.jpss.Utils;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.Transaction;

public class Advance extends Dual implements EventTarget{
	private final BigInteger average;
	private final BigInteger difference;
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
        TimeMachine.delay(Utils.normal(average, difference), this);
	}

	@Override
	public void fire(Transaction _) {
		Transaction t = tr.get(0);
		tr.remove(0);
		t.sendTo(getNext());
		
	}

}
