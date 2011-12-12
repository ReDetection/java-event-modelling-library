package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigInteger;

public class Time implements Comparable<Time> {
	
	private final BigInteger value;

	public Time(BigInteger time){
		this.value = time;
		
	}
	
	public BigInteger getTime(){
		return value;
	}

	@Override
	public int compareTo(Time o) {
		return value.compareTo(o.getTime());
	}
	
}
