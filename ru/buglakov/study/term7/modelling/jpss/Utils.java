package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigDecimal;
import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.blocks.Dual;
import ru.buglakov.study.term7.modelling.jpss.blocks.Multipoint;
import ru.buglakov.study.term7.modelling.jpss.blocks.TransactionInput;
import ru.buglakov.study.term7.modelling.jpss.blocks.TransactionOutput;

public class Utils {

	public static BigInteger normal(BigInteger average, BigInteger difference) {
		BigDecimal a = new BigDecimal(Math.random());
		a=a.multiply(new BigDecimal(2));
		a=a.subtract(BigDecimal.ONE);
		a=a.multiply(new BigDecimal(difference));
		return average.add(a.toBigInteger());
	}
	
	public static void simplyConnect(Multipoint multi, Dual block, TransactionOutput prev, TransactionInput next){
		Dual start = multi.createStart();
		Dual finish = multi.createFinish();
		
		prev.setNext(start);
		start.setNext(block);
		block.setNext(finish);
		finish.setNext(next);
	}
	
}
