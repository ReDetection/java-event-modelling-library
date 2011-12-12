package ru.buglakov.study.term7.modelling.jpss;

import java.util.HashMap;
import java.util.Map;

public class TransactionManager {
	private static Map<String,Transaction> map = new HashMap<>();

	public static Transaction createTransaction(){
		return new Transaction(createKey());
	}

	private static String createKey() {
		String k = new Double(Math.random()).toString();
		while(map.containsKey(k)){
			k = new Double(Math.random()).toString();
		}
		return k;
	}
	
}
