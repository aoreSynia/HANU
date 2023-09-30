package Tut4.Ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNums {

    // method gen ra 1 list dãy số tự nhiên 
    public static List<Integer> generateRandomNumbers(int count, int min, int max) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            // Sinh số ngẫu nhiên trong khoảng từ min đến max
            int randomNumber = random.nextInt(max - min + 1) + min;
            randomNumbers.add(randomNumber);
        }

        return randomNumbers;
    }


    public static void main(String[] args) {
        // Tạo một danh sách chứa 10 số ngẫu nhiên trong khoảng từ 1 đến 100
        List<Integer> randomNumbers = generateRandomNumbers(10, 1, 100); 
        
        // In danh sách các số ngẫu nhiên ra màn hình
        for (Integer number : randomNumbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    
    
}
