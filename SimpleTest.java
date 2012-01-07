import static ru.buglakov.study.term7.modelling.jpss.Utils.avg;
import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.TransactionManager;
import ru.buglakov.study.term7.modelling.jpss.blocks.*;

public class SimpleTest {

    public static void main(String[] args) {
        Generate g = new Generate(200, 100);
        DiffStopwatch stopwatch = new DiffStopwatch();
        MultipointSimplifier diff = new MultipointSimplifier(stopwatch,new Advance(200, 100));

        g.setNext(diff);
        diff.setNext(new Terminate());

        Start start = new Start(10000, g);
        start.run();

        System.out.println("Заявок отклонено: " + TransactionManager.getRevoked().size());
        System.out.println("Заявок обслужено: " + TransactionManager.getSuccessful().size());
        System.out.println("Среднее время заявки в системе: " + avg(stopwatch.getLog()));
        System.out.println("Время моделирования: " + TimeMachine.getTime());

    }

}
