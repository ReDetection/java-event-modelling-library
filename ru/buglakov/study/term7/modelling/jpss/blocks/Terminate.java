package ru.buglakov.study.term7.modelling.jpss.blocks;

import ru.buglakov.study.term7.modelling.jpss.Transaction;

public class Terminate implements TransactionInput {

	@Override
	public void receive(Transaction transaction) {
		transaction.invalidate();
		System.out.print("W");
	}

}
