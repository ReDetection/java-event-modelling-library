package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RandomUtils {

	public static BigInteger normal(BigInteger average, BigInteger difference) {
		BigDecimal a = new BigDecimal(Math.random());
		a=a.multiply(new BigDecimal(2));
		a=a.subtract(BigDecimal.ONE);
		a=a.multiply(new BigDecimal(difference));
//		Logger.getLogger(RandomUtils.class.getName()).log(Level.SEVERE, "random: " + a.toPlainString());
		return average.add(a.toBigInteger());
	}
	
}
