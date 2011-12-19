package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.buglakov.study.term7.modelling.jpss.exceptions.BlockIsBusyException;

public class TimeMachine {
	
	private static Time now = new Time(new BigInteger("0"));
	private static ArrayList<SimulationEvent> events = new ArrayList<>(20); //TODO: переделать на автоматически сортируемую структуру!

	public static void continue_() {
		while(!events.isEmpty()){
			SimulationEvent next = getNextEvent();
			now=next.getEventTime();
			try{
				next.fire();
			}catch (BlockIsBusyException e) {
				Logger.getLogger(TimeMachine.class.getName()).log(Level.INFO, "Заявка отклонена.", e);
			}
		}
		
	}
    
    public static void delaySome(EventTarget target){
        events.add(new SimulationEvent(getTime(), target));
    }
    
    public static void delay(BigInteger delay, EventTarget target){
        events.add(new SimulationEvent(getTime().getTime().add(delay),target));
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
        //FIXME: если максимальное время равно текущему - 30 раз пробовать и выводить исключение!
        events.remove(mini);
		return event;
	}
	
	public static Time getTime(){
		return now;
	}
}
