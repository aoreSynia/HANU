package Tut1.Exercise2;

/**
 * Exercise 2
 */
public class Counter {

     private int count;
/**
* @effects Makes this contain 0
*/
    
public Counter(){
     this.count = 0;
}


/**
*
* @effects Returns the value of this
*/
public int get(){
     return count;
}
/**
* @modifies this
* @effects Increments the value of this
*/
public void incr(){
     count++;
}

    
}
