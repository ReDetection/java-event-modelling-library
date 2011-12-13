package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer {
	private final Time begin;
	private Time end=null;
	private BigInteger diff=null;
	
	public Timer() {
		begin=TimeMachine.getTime();
	}
	
	public void stop() {
		if(end!=null){
			Logger.getLogger(Timer.class.getName()).log(Level.SEVERE,"Попытка остановить остановленный таймер!");
			new Exception().printStackTrace();
			return;
		}
		end = TimeMachine.getTime();
		diff= end.getTime().subtract(begin.getTime());
	}

	public Time getBegin() {
		return begin;
	}

	public Time getEnd() {
		return end;
	}

	public BigInteger getDiff() {
		return diff;
	}

}
