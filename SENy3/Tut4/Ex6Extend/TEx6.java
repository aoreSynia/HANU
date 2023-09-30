package Tut4.Ex6Extend;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TEx6 {

// Define a class Check with a checker method
    public static class Check {
        public boolean checker(Integer num) {
            return num % 2 != 0;
        }
    }

    private static class FilteredGen implements Iterator<Integer> {
        private LinkedList<Integer> inputList;
        private Check check;
        private Integer nextValue;

        // Constructor of filteredGen 
        public FilteredGen(LinkedList<Integer> inputList, Check check) {
            this.inputList = inputList;
            this.check = check;
            findNextValue();
        }
        
    @Override
    public boolean hasNext() { // chỉ việc check xem đã tới cuối mảng chưa 
            return nextValue != null || inputList.size() > 0;
        }

        @Override
        public Integer next() { 
            if (!hasNext()) { // nhận true false từ hasNext
                throw new NoSuchElementException();
    }
            // nhận giá trị từ findNextValue
            Integer curr = nextValue;
            findNextValue();
            return curr;
        }
        // chỗ check null các kiểu 
        private void findNextValue() {
            nextValue = null;
            while (inputList.size() > 0) {
                Integer num = inputList.pollFirst(); // PollFirst chính là pop trong list
                if (num != null && check.checker(num)) {
                    nextValue = num;
                    break;
                }
            }
        }
        
    }

    public static Iterator<Integer> filter(LinkedList<Integer> g, Check x){
        if (g == null || x == null) {
            throw new NullPointerException();
        }
        return new FilteredGen(g, x);
    }

    public static void main(String[] args) {
        // Create a collection of integers (you can use any collection type)
        LinkedList<Integer> listLinked = new LinkedList<>();
        
        listLinked.add(null);
        for (int i = 1; i < 60; i++) {
            if (i % 3 == 0) {
                listLinked.add(i);
                listLinked.add(null);
            } else {
                listLinked.add(i);
            }
            
        }
        System.out.print("Test : " );
        for (Integer integer : listLinked) {
            System.out.print(integer + " ");
        }
        System.out.println("");
        System.out.println("------------------------------------------------");


        // Create a Check instance that checks for odd numbers
        Check oddCheck = new Check();

        // Use the filter method to create an iterator for odd numbers
        Iterator<Integer> filteredIterator = filter(listLinked, oddCheck);

        // Print the filtered elements
        while (filteredIterator.hasNext()) {
            System.out.print(filteredIterator.next() + " ");
        }
    }
}
