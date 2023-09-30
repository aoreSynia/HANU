package Tut3.Ex3;

public class Ex3 {

    /*-------------------------------------------------------------------------------------------------
     * The version that throws an exception if the array is empty is better 
     * because it allows the caller of the procedure to know that the array is empty. 
     * This can help to prevent problems and ensure that the procedure is used correctly.
     * -------------------------------------------------------------------------------------------------
     * The version that returns 0 if the array is empty does not tell the caller that the array is empty.
     * This may cause problems if the caller is expecting a non-empty array.
     * ------------------------------------------------------------------------------------------------
     */

    //return 0 if array is empty
    public static int sumArray(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
    
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    //throw IllegalArgumentException if array is empty
    public static int sumArray1(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
    
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }
}

