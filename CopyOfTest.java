import static ru.buglakov.study.term7.modelling.jpss.Utils.avg;

import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.Time;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.TransactionManager;
import ru.buglakov.study.term7.modelling.jpss.blocks.*;


public class CopyOfTest {

    
    public static void main(String[] args) {
        Generate g = new Generate(200, 100);
        Advance a = new Advance(200, 100);
        DiffStopwatch stopwatch = new DiffStopwatch();
        
        Queue q = new Queue(5);
        DiffStopwatch queuestopwatch = new DiffStopwatch();
        SeizableDevice dev = new SeizableDevice();
        MultipointSimplifier device = new MultipointSimplifier(dev, a);
        MultipointSimplifier queue = new MultipointSimplifier(queuestopwatch, q);
        
        Line line = new Line().add(stopwatch.createStart()).add(queue).add(device).add(stopwatch.createFinish());
        
        g.setNext(line);
        line.setNext(new Terminate());
        
        TimeMachine.setLimit(new Time(new BigInteger("20000")));
        Start start = new Start(null, g);
        start.run();
        
        System.out.println();
        System.out.println(queuestopwatch.getLog());
        System.out.println(stopwatch.getLog());
        System.out.println("Заявок поступило: " + TransactionManager.getAll().size());
        System.out.println("Заявок обслужено: " + TransactionManager.getSuccessful().size());
        System.out.println("Заявок отклонено: " + TransactionManager.getRevoked().size());
        System.out.println("Среднее время заявки в очереди: "+avg(queuestopwatch.getLog()));
        System.out.println("Среднее время заявки в системе: "+avg(stopwatch.getLog()));
        System.out.println("Время моделирования: " + TimeMachine.getTime());
        
    }
    

}
