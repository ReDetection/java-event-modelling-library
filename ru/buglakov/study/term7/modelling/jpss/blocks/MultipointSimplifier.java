package ru.buglakov.study.term7.modelling.jpss.blocks;

import ru.buglakov.study.term7.modelling.jpss.Transaction;

public class MultipointSimplifier extends Dual {

	private Dual start;
	private Dual finish;

	public MultipointSimplifier(Multipoint multi,Dual contents) {
		start = multi.createStart();
		finish = multi.createFinish();
		
		start.setNext(contents);
		contents.setNext(finish);
	}
	
	@Override
	public void receive(Transaction transaction) {
		transaction.sendTo(start);
	}
	
	@Override
	public void setNext(TransactionInput nextBlock) {
		finish.setNext(nextBlock);
	}

    public Dual getFinish() {
        return finish;
    }

    public Dual getStart() {
        return start;
    }
    
    
}
