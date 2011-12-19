package ru.buglakov.study.term7.modelling.jpss.blocks;

import ru.buglakov.study.term7.modelling.jpss.Transaction;

public class SeizableDeviceFinish extends Dual {

	private final SeizableDevice device;
	
	protected SeizableDeviceFinish(SeizableDevice device) {
		this.device = device;
		
	}
	
	@Override
	public void receive(Transaction transaction) {
		device.release(transaction);
		transaction.sendTo(getNext());
	}

}
