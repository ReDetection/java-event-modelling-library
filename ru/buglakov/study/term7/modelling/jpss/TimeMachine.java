package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigInteger;
import java.util.ArrayList;

public class TimeMachine {
	
	private static Time now = new Time(new BigInteger("0"));
	private static ArrayList<SimulationEvent> events = new ArrayList<>(20);
	
	public static void startSimulation(){
		
	}
	
	public static void registerEvent(SimulationEvent e){
		if(now.compareTo(e.getEventTime())>0){ //если время уже прошло, надо бросить эксепшн
			throw new RuntimeException("Беда, время прошло уже");
		}
		synchronized (events) {
			events.add(e);
		}
	}
	
	
	
	
	
	
	
	public static Time getTime(){
		return now;
	}
	
	

}
