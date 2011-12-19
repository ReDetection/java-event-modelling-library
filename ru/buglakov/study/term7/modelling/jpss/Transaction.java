package ru.buglakov.study.term7.modelling.jpss;

import ru.buglakov.study.term7.modelling.jpss.blocks.TransactionInput;

public class Transaction {
	public static int STATUS_ALIVE = 1;
	public static int STATUS_TERMINATED = 2;
	public static int STATUS_INVALIDATED = 3;

	private final String key; 
	private final Time creationTime;
	private TransactionInput iAmAt = null;
	private int status;
	

	protected Transaction(String key) {
		this.key = key;
		creationTime = TimeMachine.getTime();
		status = STATUS_ALIVE;
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
	
	public void terminate(){
		TransactionManager.terminate(this);
		status = STATUS_TERMINATED;
	}
    
    public void invalidate(){
        TransactionManager.invalidate(this);
		status = STATUS_INVALIDATED;
    }

	@Override
	public int hashCode() {
		return key.hashCode();
	}

	public Time getCreationTime() {
		return creationTime;
	}
	
	public int getStatus() {
		return status;
	}
}
