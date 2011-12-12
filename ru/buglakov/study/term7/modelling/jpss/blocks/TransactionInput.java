package ru.buglakov.study.term7.modelling.jpss.blocks;

import ru.buglakov.study.term7.modelling.jpss.Transaction;

public interface TransactionInput {

	/**
	 * @deprecated Внимание! Не передавать транзакцию напрямую блоку через этот метод - правильно в самом транзакте вызвать метод send()!
	 */
	public void receive(Transaction transaction);
	
}
