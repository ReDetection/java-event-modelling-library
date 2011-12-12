import ru.buglakov.study.term7.modelling.jpss.blocks.Generate;
import ru.buglakov.study.term7.modelling.jpss.blocks.Start;
import ru.buglakov.study.term7.modelling.jpss.blocks.Terminate;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Generate[] g = new Generate[1];
		g[0] = new Generate(1000, 100);
		g[0].setNext(new Terminate());
		Start start = new Start(10, g);
		start.run();
	}

}
