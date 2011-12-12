package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;

public class Start {
	
	private final BigInteger count;
	private final Generate[] blocks;

	public Start(BigInteger count, Generate[] blocks) {
		this.count = count;
		this.blocks = blocks;
	}
	
	void run(){
		for(Generate g: blocks){
			g.start(count);
		}
		
	}
	
	
}
