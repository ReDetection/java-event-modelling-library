package ru.buglakov.study.term7.modelling.jpss.blocks;

import ru.buglakov.study.term7.modelling.jpss.Transaction;
import ru.buglakov.study.term7.modelling.jpss.exceptions.BlockIsBusyException;

public class SeizableDeviceStart extends Dual {

	private final SeizableDevice device;
	
	protected SeizableDeviceStart(SeizableDevice device) {
		this.device = device;
		
	}
	
	@Override
	public void receive(Transaction transaction)throws BlockIsBusyException {
		device.seize(transaction);
		transaction.sendTo(getNext());
	}

}
