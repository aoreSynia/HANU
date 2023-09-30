package Tut2;

import Tut1.Exercise2.Counter;

public class Counter2 extends Counter {
    
    /**
    * @effects: Makes this contain 0.
    */
    public Counter2(){
        super();
    }
    
    /**
    * @modifies this
    * @effects Makes this contain twice its current value.
    */
    @Override
    public void incr(){
        
        int count2 = get();
        count2*=2;
    }
}
