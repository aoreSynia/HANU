package Tut4.Ex6Extend;

import java.util.LinkedList;
import java.util.Random;

public class PrimeList extends LinkedList<Integer> {
    // Constructor để tự động tạo danh sách số nguyên tố trong khoảng [1, n]
    public PrimeList(int n) {
        for (int i = 2; i <= n; i++) {
            if (isPrime(i, 5)) {
                add(i);
            }
        }
    }

    // Phương thức kiểm tra xem một số có phải là số nguyên tố không
    // Phương thức của Miller =)) trông hay quá nên nhét vô... k hiểu lắm
    private boolean isPrime(int n, int number_Of_Tests) {
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

        for (int i = 0; i < number_Of_Tests; i++) {
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

    public static void main(String[] args) {
        int n = 50; // Đổi giá trị này tùy ý
        PrimeList primeList = new PrimeList(n);
        System.out.println("Danh sách số nguyên tố từ 1 đến " + n + ": " + primeList);
    }
}

