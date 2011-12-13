package ru.buglakov.study.term7.modelling.jpss.blocks;

public abstract class TransactionOutput {

	private TransactionInput next;

	public TransactionInput getNext() {
		return next;
	}

	public void setNext(TransactionInput nextBlock) {
		if(this.next!=null){
			throw new RuntimeException("Переопределение следующего блока!");
		}
		this.next= nextBlock;
	}
	
}
