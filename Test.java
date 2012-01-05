import static ru.buglakov.study.term7.modelling.jpss.Utils.avg;

import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.Time;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.blocks.*;


public class Test {

	
	public static void main(String[] args) {
		Generate g = new Generate(200, 100);
		Advance a = new Advance(200, 100);
		SimpleStopwatch simpleStopwatch =  new SimpleStopwatch(); 
		SimpleStopwatch simpleStopwatch2 =  new SimpleStopwatch(); 
		DiffStopwatch stopwatch = new DiffStopwatch();
		DiffStopwatchStart stopwatchStart = stopwatch.createStart();
		DiffStopwatchFinish stopwatchFinish = stopwatch.createFinish();
        
        Queue q = new Queue();
		DiffStopwatch queuestopwatch = new DiffStopwatch();
        SeizableDevice dev = new SeizableDevice();
        MultipointSimplifier device = new MultipointSimplifier(dev, a);
        MultipointSimplifier queue = new MultipointSimplifier(queuestopwatch, q);
        
		
		g.setNext(simpleStopwatch);
        simpleStopwatch.setNext(stopwatchStart);
		stopwatchStart.setNext(queue);
        queue.setNext(simpleStopwatch2);
        simpleStopwatch2.setNext(device);
        device.setNext(stopwatchFinish);
        
        
		stopwatchFinish.setNext(new Terminate());
		
		TimeMachine.setLimit(new Time(new BigInteger("20000")));
		Start start = new Start(null, g);
		start.run();
        System.out.println();
        System.out.println(simpleStopwatch.getLog());
        System.out.println(simpleStopwatch2.getLog());
        System.out.println(queuestopwatch.getLog());
        System.out.println(stopwatch.getLog());
        System.out.println("Среднее время заявки в очереди: "+avg(queuestopwatch.getLog()));
        System.out.println("Среднее время заявки в системе: "+avg(stopwatch.getLog()));
        System.out.println("Время моделирования: " + TimeMachine.getTime());
        
	}
    

}
