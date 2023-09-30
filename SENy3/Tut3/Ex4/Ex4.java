package Tut3.Ex4;

public class Ex4 {
    public static void combine(int[] a, int[] b) {
        // Preconditions: Check if 'a' or 'b' is null
        if (a == null || b == null) {
            throw new IllegalArgumentException("Input arrays 'a' and 'b' must not be null.");
        }
        
        // Calculate the sum of elements of b
        int sumOfB = 0;
        for (int num : b) {
            sumOfB += num;
        }
    
        // Multiply each element of a by the sum of b
        for (int i = 0; i < a.length; i++) {
            a[i] *= sumOfB;
        }
    }
}
