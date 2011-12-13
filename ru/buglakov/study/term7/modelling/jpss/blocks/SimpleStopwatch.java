package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

import ru.buglakov.study.term7.modelling.jpss.TimeMachine;
import ru.buglakov.study.term7.modelling.jpss.Transaction;

/**
 * Блок, просто засекающий текущее время, когда мимо него проходит транзакт
 * @author rd
 */
public class SimpleStopwatch extends Dual{
    private List<BigInteger> log = new ArrayList<>();
    
    @Override
    public void receive(Transaction transaction) {
        log.add(TimeMachine.getTime().getTime());
        transaction.sendTo(getNext());
    }

    public List<BigInteger> getLog() {
        return log;
    }
    
}
