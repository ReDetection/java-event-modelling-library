package ru.buglakov.study.term7.modelling.jpss.blocks;


import ru.buglakov.study.term7.modelling.jpss.Transaction;

public class DiffStopwatchStart extends Dual {
	private final DiffStopwatch model;

    protected DiffStopwatchStart(DiffStopwatch model) {
		this.model = model;
	}
    
    @Override
    public void receive(Transaction transaction) {
    	model.start(transaction.getKey());
//    	if(prev!=null){
//    		Logger.getLogger(DiffStopwatchStart.class.getName()).log(Level.WARNING, 
//    				"Транзакция " +transaction.getKey() +" от " + transaction.getCreationTime() + " попала в счетчик "
//    				+DiffStopwatchStart.class.getName() + " не первый раз!");
//    		
//    	}
    	transaction.sendTo(getNext());
    }
    
    
}
