package Tut4.Ex5;

// import java.util.Iterator;
// import java.util.LinkedList;
// import java.util.NoSuchElementException;

// public class IntegerLinkedList extends LinkedList<Integer>{
//     private class EvenIterator implements Iterator<Integer> {
//         private int cur = -2;

//         @Override
//         public boolean hasNext() {
//             // TODO Auto-generated method stub
//             return(cur + 2 ) < size();
//         }

//         @Override
//         public Integer next() {
//             // TODO Auto-generated method stub
//             if (!hasNext()) {
//                 throw new NoSuchElementException();
//             }
//             cur += 2;
//             return get(cur);

//         }


        
        
//     }
// }
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class IntegerLinkedList extends LinkedList<Integer> {

    // Custom Iterator for even elements
    private class EvenIterator implements Iterator<Integer> {
        private int curr = -2; // Start before the first even element (-2 because we want to skip odd elements)

        @Override
        public boolean hasNext() {
            // Check if there is an even element after the current index
            return (curr + 2) < size();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            curr += 2; // Move to the next even element (skip one odd element)
            return get(curr);
        }
    }

    // Override the iterator method to return the custom EvenIterator
    @Override
    public Iterator<Integer> iterator() {
        return new EvenIterator();
    }

    public static void main(String[] args) {
        IntegerLinkedList integerList = new IntegerLinkedList();
        Iterator<Integer> evenIterator = integerList.iterator();

        for (int i = 1; i < 20; i++) {
            integerList.add(i);
        }

        System.out.println("Even elements:");
        while (evenIterator.hasNext()) {
            System.out.println(evenIterator.next());
        }
    }
}
