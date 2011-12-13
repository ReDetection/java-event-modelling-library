
package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.math.BigInteger;
import ru.buglakov.study.term7.modelling.jpss.Transaction;


public class Counter extends Dual {
    
    private BigInteger counter = BigInteger.ZERO;

    @Override
    public void receive(Transaction transaction) {
        counter = counter.add(BigInteger.ONE);
        transaction.sendTo(getNext());
    }

    public BigInteger getCounter() {
        return counter;
    }
     
}
