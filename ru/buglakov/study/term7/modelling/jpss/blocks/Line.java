package ru.buglakov.study.term7.modelling.jpss.blocks;

import ru.buglakov.study.term7.modelling.jpss.Transaction;

public class Line extends Dual{
	private Dual first = null;
	private Dual last = null;
	private TransactionInput nextBlock = null;
	
	public Line add(Dual block){
		if(first==null){
			last=first=block;
		}else{
			last.setNext(block);
			last=block;
			if(nextBlock!=null){
				last.setNext(nextBlock);
			}
		}
		return this;
	}

	@Override
	public void receive(Transaction transaction) {
		transaction.sendTo(first);
	}
	
	@Override
	public void setNext(TransactionInput nextBlock) {
		this.nextBlock = nextBlock;
		if(last!=null){
			last.setNext(nextBlock);
		}
	}
}
