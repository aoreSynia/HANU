package Tut2;

import Tut1.Exercise2.Counter;

public class Counter3 extends Counter {

    /**
    * @effects: Makes this contain 0.
    */
    public Counter3(){
        super();
    }

    /**
     * 
    * @modifies this
    * @effects Makes this contain twice its current value.
    */
    public void incr(int n){
        int count3 = get();
        if(n > 0){
            count3 += n;
        }
    }
}
