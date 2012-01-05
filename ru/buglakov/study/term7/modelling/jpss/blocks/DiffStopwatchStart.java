package ru.buglakov.study.term7.modelling.jpss.blocks;


import ru.buglakov.study.term7.modelling.jpss.Transaction;
import ru.buglakov.study.term7.modelling.jpss.exceptions.BlockIsBusyException;

public class DiffStopwatchStart extends Dual {
	private final DiffStopwatch model;

    protected DiffStopwatchStart(DiffStopwatch model) {
		this.model = model;
	}
    
    @Override
    public void receive(Transaction transaction) {
    	model.start(transaction.getKey());
    	try{
            transaction.sendTo(getNext());
        }catch(BlockIsBusyException e){
            model.cancelStart(transaction.getKey());
            throw e;
        }
    }
    
    
}
