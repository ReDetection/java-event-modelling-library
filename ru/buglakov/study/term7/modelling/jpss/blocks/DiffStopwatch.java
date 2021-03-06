package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.buglakov.study.term7.modelling.jpss.Timer;

public class DiffStopwatch implements Multipoint{
	private Map<String,Timer> map = new HashMap<>();
	private List<LogEntry> log = new ArrayList<>();
	
	@Override
	public DiffStopwatchStart createStart(){
		return new DiffStopwatchStart(this);
	}

	@Override
	public DiffStopwatchFinish createFinish(){
		return new DiffStopwatchFinish(this);
	}
	
	protected void start(String transactionKey){
		Timer t = new Timer();
		Timer prev = map.put(transactionKey, t);
		if(prev!=null){
    		Logger.getLogger(DiffStopwatch.class.getName()).log(Level.WARNING, 
    				"Транзакция " +transactionKey + " попала в счетчик "
    				+DiffStopwatch.class.getName() + " до того, как вышла!");
		}
	}

    void cancelStart(String key) {
        map.remove(key);
    }
	
	protected LogEntry stop(String transactionKey) {
		Timer t = map.get(transactionKey);
		t.stop();
		LogEntry entry = new LogEntry(transactionKey,t);
		log.add(entry);
		return entry;
	}
	
	public class LogEntry{
		public final String key;
		public final Timer timer;
		public LogEntry(String key, Timer timer) {
			this.key = key;
			this.timer = timer;
		}
		@Override
		public String toString() {
			return "\n" + " " + timer.getDiff() ;
		}
		
	}
	
	public List<LogEntry> getFullLog() {
		return log;
	}
    
    public List<BigInteger> getLog(){
        ArrayList<BigInteger> result =  new ArrayList<>(log.size());
        for(LogEntry e : log){
            result.add(e.timer.getDiff());
        }
        return result;
    }
	
}
