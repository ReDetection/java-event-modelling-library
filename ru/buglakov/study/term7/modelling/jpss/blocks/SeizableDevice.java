package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import ru.buglakov.study.term7.modelling.jpss.Transaction;
import ru.buglakov.study.term7.modelling.jpss.exceptions.BlockIsBusyException;

public class SeizableDevice implements Multipoint {
	
	private Boolean busy = false;
	private Transaction t=null;

	@Override
	public SeizableDeviceStart createStart() {
		return new SeizableDeviceStart(this);
	}

	@Override
	public Dual createFinish() {
		return new SeizableDeviceFinish(this);
	}
	
	protected void seize(Transaction t) throws BlockIsBusyException{
		synchronized (busy) {
			if(busy){ 
				if(t.getStatus()!=Transaction.STATUS_ALIVE){
					Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Транзакция вышла из системы, не освободив устройство. Нарушится статистика!", new Exception());
					this.t = t;
				}else{
					throw new BlockIsBusyException();
				}
			}else{
				busy = true;
				this.t = t;
			}
		}
	}
	
	protected void release(Transaction t) throws BlockIsBusyException{
		synchronized (busy) {
			if(busy){
				busy = false;
				this.t = null;
			}else{
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Попытка освободить свободное устройство!");
				throw new RuntimeException("Попытка освободить свободное устройство!");
			}
		}
	}

}
