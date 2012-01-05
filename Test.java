import static ru.buglakov.study.term7.modelling.jpss.Utils.avg;

import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.Time;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.TransactionManager;
import ru.buglakov.study.term7.modelling.jpss.blocks.*;


public class Test {

	
	public static void main(String[] args) {
		Generate g = new Generate(200, 100);
		Advance a = new Advance(200, 100);
		SimpleStopwatch simpleStopwatch =  new SimpleStopwatch(); 
		SimpleStopwatch simpleStopwatch2 =  new SimpleStopwatch(); 
		DiffStopwatch stopwatch = new DiffStopwatch();
		Counter cstart = new Counter(), cend = new Counter(); 
        
        Queue q = new Queue(5);
		DiffStopwatch queuestopwatch = new DiffStopwatch();
        SeizableDevice dev = new SeizableDevice();
        MultipointSimplifier device = new MultipointSimplifier(dev, a);
        MultipointSimplifier queue = new MultipointSimplifier(queuestopwatch, q);
        
        Line line = new Line().add(cstart).add(simpleStopwatch).add(stopwatch.createStart()).add(queue).add(simpleStopwatch2).add(device).add(stopwatch.createFinish()).add(cend);
		
		g.setNext(line);
		line.setNext(new Terminate());
		
		TimeMachine.setLimit(new Time(new BigInteger("20000")));
		Start start = new Start(null, g);
		start.run();
        System.out.println();
        System.out.println(simpleStopwatch.getLog());
        System.out.println(simpleStopwatch2.getLog());
        System.out.println(queuestopwatch.getLog());
        System.out.println(stopwatch.getLog());
        System.out.println("Заявок поступило: " + cstart.getCounter());
        System.out.println("Заявок обслужено: " + cend.getCounter());
        System.out.println("Заявок отклонено: " + TransactionManager.getRevoked().size());
        System.out.println("Среднее время заявки в очереди: "+avg(queuestopwatch.getLog()));
        System.out.println("Среднее время заявки в системе: "+avg(stopwatch.getLog()));
        System.out.println("Время моделирования: " + TimeMachine.getTime());
        
	}
    

}
