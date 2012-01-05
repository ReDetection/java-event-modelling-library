package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.util.LinkedList;

import ru.buglakov.study.term7.modelling.jpss.EventTarget;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.Transaction;
import ru.buglakov.study.term7.modelling.jpss.exceptions.BlockIsBusyException;

public class Queue extends Dual implements EventTarget{
	//TODO: logging
	
	private LinkedList<Transaction> queue = new LinkedList<>();

	private Integer maxCount;
	
	public Queue() {
		this(null);
	}
	
	public Queue(Integer maxSize){
		maxCount = maxSize;
	}


	public Integer getMaxCount() {
		return maxCount;
	}

	@Override
	public void receive(Transaction transaction) {
		if(queue.isEmpty()){
			try{
				transaction.sendTo(getNext());
			}catch (BlockIsBusyException e) {
				queue.addLast(transaction);
				TimeMachine.delaySome(this);
			}
		}else{
			if(maxCount==null || maxCount > queue.size()){
				queue.addLast(transaction);
			}else{
				transaction.invalidate();
			}
		}
		
	}

	@Override
	public void fire(Transaction t_) {
		try{
			while(!queue.isEmpty()){
				Transaction first = queue.getFirst();
				first.sendTo(getNext());
				queue.removeFirst();
			}
		}catch (BlockIsBusyException e) {
			TimeMachine.delaySome(this);
		}
		
	}
	
	
}
