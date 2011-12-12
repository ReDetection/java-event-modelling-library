package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class TimeMachine {
	
	private static Time now = new Time(new BigInteger("0"));
	private static ArrayList<SimulationEvent> events = new ArrayList<>(20); //TODO: переделать на автоматически сортируемую структуру!
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
		
	public static void continue_() {
		while(!events.isEmpty()){
			SimulationEvent next = getNextEvent();
			//TODO: increase all timestats!
			now=next.getEventTime();
			next.fire();
		}
		
	}

	private static SimulationEvent getNextEvent(){
		SimulationEvent event = events.get(0);
		int mini = 0;
		int i=1;
		for(;i<events.size();i++){
			SimulationEvent e2 = events.get(i);
			if(e2.getEventTime().compareTo(event.getEventTime())<0){
				event=e2;
				mini=i;
			}
		}
		events.remove(mini);
		return event;
	}
	
	public static Time getTime(){
		return now;
	}

	

}
