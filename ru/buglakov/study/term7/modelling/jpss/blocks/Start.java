package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.TimeMachine;

public class Start {
	
	private final BigInteger count;
	private final Generate[] blocks;

	public Start(long count, Generate[] blocks) {
		this(new BigInteger(new Long(count).toString()),blocks);
	}
	
	public Start(BigInteger count, Generate[] blocks) {
		this.count = count;
		this.blocks = blocks;
	}
	
	public void run(){
		for(Generate g: blocks){
			g.start(count);
		}
		TimeMachine.continue_();
	}
	
	
}
