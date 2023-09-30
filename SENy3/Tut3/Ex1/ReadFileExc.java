package Tut3.Ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFileExc {
    public static void main(String[] args) throws IOException {
        
        // Đọc và viết file !! đã dùng relative path ==> vấn đề ở đây liên quan tới IDE đang "BẮT ĐẦU" đọc ở folder nào

        // Note for me : phải đọc từ file code thì nó mới hiểu relative path bên dưới.
        FileReader fr = new FileReader("SENy3\\Tut3\\Ex1\\InputFile.txt");
        BufferedReader pr = new BufferedReader(fr);

        FileWriter fw = new FileWriter("SENy3\\Tut3\\Ex1\\OutputFile.txt");

        // Đọc từng dòng một nếu như vẫn còn dữ liệu sau đó xoá hết space đi
        String line;
        while ((line = pr.readLine()) != null) {
            String output = line.replaceAll("\\s+", "");
            fw.write(output);
            fw.write(System.lineSeparator()); // Add a newline after each line
        }

        fr.close();
        pr.close();
        fw.close();

        System.out.println("File successfully processed.");
    }
}
