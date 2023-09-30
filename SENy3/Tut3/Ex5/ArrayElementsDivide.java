package Tut3.Ex5;


public class ArrayElementsDivide {
    public static void main(String[] args) {
        // Test Case 1 (Chia các phần tử):
        
        // int[] a = {10, 20, 30, 40, 50};
        // int[] b = {2, 4, 6, 8, 10};

        // Test Case 2 (Chia cho 0):

        int[] a = {10, 20, 30, 40, 50};
        int[] b = {2, 0, 6, 8, 10};

        // Test Case 3 (Số phần tử của mảng b ít hơn mảng a):

        // int[] a = {10, 20, 30, 40, 50};
        // int[] b = {2, 4, 6};

        // Test Case 4 (Số phần tử của mảng b nhiều hơn mảng a):

        // int[] a = {10, 20, 30};
        // int[] b = {2, 4, 6, 8, 10};


        try {
            divide(a, b);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
        }
    }

    public static void divide(int[] a, int[] b) throws IndexOutOfBoundsException, ArithmeticException {
        if (b.length < a.length) {
            throw new IndexOutOfBoundsException("Array B không tương đồng về số lượng phần tử với A");
        }

        for (int i = 0; i < a.length; i++) {
            if (b[i] == 0) {
                throw new ArithmeticException("Division by zero at index " + i);
            }

            a[i] /= b[i];
        }
        

        for (int element : a) {
            System.out.print(element + " ");
        }
    }
}
