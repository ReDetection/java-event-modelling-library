package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.EventTarget;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.Transaction;

public class SeizableDeviceFinish extends Dual implements EventTarget{

	private final SeizableDevice device;
	
	protected SeizableDeviceFinish(SeizableDevice device) {
		this.device = device;
		
	}
	
	@Override
	public void receive(Transaction transaction) {
        TimeMachine.delay(BigInteger.ZERO, this,transaction);
		
	}

    @Override
    public void fire(Transaction linked) {
        device.release(linked);
		linked.sendTo(getNext());
    }

}
