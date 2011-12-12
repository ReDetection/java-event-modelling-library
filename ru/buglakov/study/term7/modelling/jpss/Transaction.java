package ru.buglakov.study.term7.modelling.jpss;

public class Transaction {
	private final String key; 
	

	protected Transaction(String key) {
		this.key = key;
	}


	public String getKey() {
		return key;
	}
	
	
}
