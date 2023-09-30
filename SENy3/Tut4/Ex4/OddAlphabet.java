package Tut4.Ex4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class OddAlphabet {
    public static List<Character> gen_letterList(){
        List<Character> gen_letterList = new ArrayList<Character>();
        // k hiểu thì thôi này =))) 
        for (char i = 'a'; i <= 'z'; i++) {
                gen_letterList.add(i);
        }
        return gen_letterList;
        
    }

    // print method with iterator
    public static List<Character> genNew_OddList_Withterator(List<Character> list) {
        // tạo ra iterator cho list được nhận vào từ parameter 
        Iterator<Character> iterator = list.iterator();
        // tạo list mới cho method này
        List<Character> oddChar_list = new ArrayList<Character>();
        while (iterator.hasNext()) {
            char character = iterator.next();
            if (character % 2 != 0) { // Kiểm tra xem mã ký tự có là số lẻ không
                oddChar_list.add(character);
            }
        }
        return oddChar_list;
    }

    // in 
    public static void print_WithIterator(List<Character> list){
        Iterator<Character> iterator = list.iterator();
        
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
            
        }
        System.out.println("");
    }

    public static void main(String[] args) {

        List<Character> l1 = gen_letterList();
        List<Character> l2 = genNew_OddList_Withterator(l1);

        System.out.print("List 1 : ");
        print_WithIterator(l1);

        System.out.print("List 2 : ");
        print_WithIterator(l2);
        
        
    }
}
