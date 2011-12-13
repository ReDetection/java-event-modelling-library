import ru.buglakov.study.term7.modelling.jpss.blocks.*;


public class Test {

	
	public static void main(String[] args) {
		Generate[] g = new Generate[1];
		g[0] = new Generate(200, 100);
		Advance a = new Advance(200, 100);
		
		
		DiffStopwatch stopwatch = new DiffStopwatch();
		DiffStopwatchStart stopwatchStart = stopwatch.createStart();
		g[0].setNext(stopwatchStart);
		stopwatchStart.setNext(a);
		DiffStopwatchFinish stopwatchFinish = stopwatch.createFinish();
        a.setNext(stopwatchFinish);
		
		
        Counter c = new Counter();
		stopwatchFinish.setNext(c);
        c.setNext(new Terminate());
		Start start = new Start(10, g);
		start.run();
        System.out.println();
        System.out.println(c.getCounter());
        System.out.println(stopwatch.getLog());
	}

}
