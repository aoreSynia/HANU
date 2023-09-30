package Tut4.Ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class First10Prime {
    private static List<Integer> ls = new ArrayList<Integer>() {};

    // Miller Rabin algorithm :
    public static boolean isPrime(int n, int k) {
        if (n <= 1 || n == 4) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        int d = n - 1;
        while (d % 2 == 0) {
            d /= 2;
        }

        for (int i = 0; i < k; i++) {
            if (!millerTest(d, n)) {
                return false;
            }
        }
        return true;
    }

    private static boolean millerTest(int d, int n) {
        Random rand = new Random();
        int a = 2 + rand.nextInt(n - 4);
        int x = (int) Math.pow(a, d) % n;

        if (x == 1 || x == n - 1) {
            return true;
        }

        while (d != n - 1) {
            x = (x * x) % n;
            d *= 2;

            if (x == 1) {
                return false;
            }
            if (x == n - 1) {
                return true;
            }
        }
        return false;
        

        
    }

    

    // public static void main(String[] args) {
    //     int element = 2; // Số cần kiểm tra
    //     int number_Of_Tests = 5; // Số lần kiểm tra

    //     while (ls.size() < 10) {
    //         if (isPrime(element, number_Of_Tests)) {
    //             ls.add(element);
    //         }
    //         element++;
            
    //     }
    //     System.out.println("The first 10 prime numbers are:");
    //     for (int prime : ls) {
    //         System.out.print(prime + " ");
    //     }
        
    // }

}
