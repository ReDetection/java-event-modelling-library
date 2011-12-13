package ru.buglakov.study.term7.modelling.jpss;

import ru.buglakov.study.term7.modelling.jpss.blocks.TransactionInput;

public class Transaction {
	private final String key; 
	private final Time creationTime;
	private TransactionInput iAmAt = null;
	

	protected Transaction(String key) {
		this.key = key;
		creationTime = TimeMachine.getTime();
	}
	
	@SuppressWarnings("deprecation")
	public void sendTo(TransactionInput next) {
		iAmAt = next;
		next.receive(this);
	}


	public String getKey() {
		return key;
	}
	
	public TransactionInput whereAreYou(){
		return iAmAt;
	}
	
	public void invalidate(){
		TransactionManager.invalidate(this);
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}

	public Time getCreationTime() {
		return creationTime;
	}
	
	
}
