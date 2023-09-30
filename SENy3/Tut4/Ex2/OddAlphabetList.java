package Tut4.Ex2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class OddAlphabetList {
    public static List<Character> create_Odd_letterList(){
        List<Character> odd_Alphabet_List = new ArrayList<Character>();
        // k hiểu thì thôi này =))) 
        for (char i = 'a'; i <= 'z'; i++) {
            if (i % 2 != 0) {
                odd_Alphabet_List.add(i);
            }
        }
        return odd_Alphabet_List;
        
    }

    // print method with iterator
    public static void printListWithIterator(List<Character> list) {
        // tạo ra iterator cho list được nhận vào từ parameter 
        Iterator<Character> iterator = list.iterator();
        while (iterator.hasNext()) { // check xem phần tử tiếp theo có phải là null không
            System.out.print(iterator.next() + " "); // .next là pointer ( giống linked list )
        }
        System.out.println();
    }
     

    public static void main(String[] args) {
        List<Character> ls = create_Odd_letterList();
        for (char i : ls) {
            System.out.print(i+" ");
        }
    }
}
