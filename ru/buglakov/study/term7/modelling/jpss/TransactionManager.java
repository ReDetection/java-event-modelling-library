package ru.buglakov.study.term7.modelling.jpss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionManager {
	private static Map<String,Transaction> map = new HashMap<>();
	private static List<Transaction> tr = new ArrayList<Transaction>(20);
    private static List<Transaction> revoked = new ArrayList<Transaction>(20);
    private static List<Transaction> successful = new ArrayList<Transaction>(20);
    

	public static Transaction createTransaction(){
		Transaction t = new Transaction(createKey());
		map.put(t.getKey(), t);
		tr.add(t);
		System.out.print('-');
		return t;
	}

	private static String createKey() {
		String k = new Double(Math.random()).toString();
		while(map.containsKey(k)){
			k = new Double(Math.random()).toString();
		}
		return k;
	}
	
	public static void terminate(Transaction t){
		tr.remove(t);
        successful.add(t);
	}

    static void invalidate(Transaction t) {
        tr.remove(t);
        revoked.add(t);
    }

    public static List<Transaction> getRevoked() {
        return revoked;
    }

    public static List<Transaction> getSuccessful() {
        return successful;
    }
	
}
