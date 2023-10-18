package Tut4.Ex6;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class PrimeLinkedList extends LinkedList<Integer> {

    // Định nghĩa một iterator cho các số nguyên tố
    public Iterator<Integer> primeIterator() {
        return new PrimeIterator();
    }

    // Lớp bên trong cho iterator của các số nguyên tố
    private class PrimeIterator implements Iterator<Integer> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentIndex < size()) {
                if (isPrime(get(currentIndex))) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(currentIndex++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        // Phương thức hỗ trợ kiểm tra một số có phải là số nguyên tố
        private boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }
            if (number <= 3) {
                return true;
            }
            if (number % 2 == 0 || number % 3 == 0) {
                return false;
            }
            int i = 5;
            while (i * i <= number) {
                if (number % i == 0 || number % (i + 2) == 0) {
                    return false;
                }
                i += 6;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        PrimeLinkedList primeList = new PrimeLinkedList();
        for (int i = 1; i < 20; i++) {
            primeList.add(i);
        }
        
        Iterator<Integer> primeIterator = primeList.primeIterator();
        while (primeIterator.hasNext()) {
            Integer prime = primeIterator.next();
            System.out.println(prime);
        }
    }
}
