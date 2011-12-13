package ru.buglakov.study.term7.modelling.jpss.blocks;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.buglakov.study.term7.modelling.jpss.Time;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
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
