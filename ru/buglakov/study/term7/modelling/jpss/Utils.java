package ru.buglakov.study.term7.modelling.jpss;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Collection;


public class Utils {

	public static BigInteger normal(BigInteger average, BigInteger difference) {
		BigDecimal a = new BigDecimal(Math.random());
		a=a.multiply(new BigDecimal(2));
		a=a.subtract(BigDecimal.ONE);
		a=a.multiply(new BigDecimal(difference));
		return average.add(a.toBigInteger());
	}
	  
    public static BigInteger sum(Collection<BigInteger> log){
        BigInteger result = BigInteger.ZERO;
        for(BigInteger a: log){
            result = result.add(a);
        }
        return result;    
    }
    
    public static BigDecimal avg(Collection<BigInteger> log){
    	if(!log.isEmpty()){
            return new BigDecimal(sum(log)).divide(new BigDecimal(log.size()),5,RoundingMode.HALF_UP);
    	}
    	return null;
    }
}
