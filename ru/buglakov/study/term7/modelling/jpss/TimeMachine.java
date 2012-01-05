package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.buglakov.study.term7.modelling.jpss.exceptions.BlockIsBusyException;

public class TimeMachine {
    
	private static Time now = new Time(new BigInteger("0"));
	private static ArrayList<SimulationEvent> events = new ArrayList<>(20); //TODO: переделать на автоматически сортируемую структуру!

    private static Time limit = null;
    
    
	public static void continue_() {
		while(!events.isEmpty()){
			SimulationEvent next = getNextEvent();
            events.remove(next);
			now=next.getEventTime();
            if(limit!=null){
                if(limit.compareTo(now)<0){
                    now=limit;
                    break;
                }
            }
			try{
				next.fire();
			}catch (BlockIsBusyException e) {
				Logger.getLogger(TimeMachine.class.getName()).log(Level.INFO, "Заявка отклонена.", e);
			}
		}
		
	}
    
    public static void delaySome(EventTarget target){
        /* FIXME: проанализировать возможные пути задержек. Кажется, тут могут быть дедлоки.
         *
         * по-моему, надо сделать отдельную очередь для таких задержек.
         * если не останется простых - то тогда им назначать время ближайшей простой задержки
         *
         * возможно, придется перегружать метод с параметром "какой блок ждем".
         * если так, нужно думать хорошую архитектуру такого решения
         */
        SimulationEvent e = new SimulationEvent(getNextEvent().getEventTime(), target);
        events.add(e);
    }
    
    public static void delay(BigInteger delay, EventTarget target){
        events.add(new SimulationEvent(getTime().getTime().add(delay),target));
    }
    
    public static void delay(BigInteger delay, EventTarget target, Transaction linked){
        events.add(new SimulationEvent(getTime().getTime().add(delay),target,linked));
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
        return event;
	}
	
	public static Time getTime(){
		return now;
	}

    public static Time getLimit() {
        return limit;
    }

    public static void setLimit(Time newlimit) {
        limit = newlimit;
    }
    
}
