package ru.buglakov.study.term7.modelling.jpss.blocks;

import ru.buglakov.study.term7.modelling.jpss.EventTarget;

/**
 *
 * @author rd
 */

//FIXME: продумать архитекруту
public interface Waitable {
    
    public void registerMe(EventTarget w);
    
}
