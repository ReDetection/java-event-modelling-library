package ru.buglakov.study.term7.modelling.jpss;

import ru.buglakov.study.term7.modelling.jpss.blocks.TransactionInput;

public class Transaction {
	private final String key; 
	private TransactionInput iAmAt = null;
	

	protected Transaction(String key) {
		this.key = key;
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
	
	
}
