package a1_2101140062;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Init {
public static void main(String[] args) {
        // Replace 'sample.txt' with the path to your text file.
        String filePath = "a1_2101140062\\project\\src\\a1_2101140062\\testcase.txt";

        try {
            // Read the entire file into a list of lines.
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Initialize variables to store title and body.
            StringBuilder title = new StringBuilder();
            StringBuilder body = new StringBuilder();

            // Iterate through the lines.
            for (String line : lines) {
                // If the line is not empty, append it to the appropriate section.
                if (!line.trim().isEmpty()) {
                    if (title.length() == 0) {
                        title.append(line);
                    } else {
                        body.append(line).append("\n");
                    }
                } else {
                    // When a blank line is encountered, it separates title and body.
                    if (title.length() > 0) {
                        // Process the title and body as needed.
                        System.out.println("Title: " + title.toString());
                        System.out.println("Body: " + body.toString());
                        System.out.println("-----------------------------");

                        // Reset title and body for the next section.
                        title.setLength(0);
                        body.setLength(0);
                    }
                }
            }

            // Process the last section (if any).
            if (title.length() > 0) {
                System.out.println("Title: " + title.toString());
                System.out.println("Body: " + body.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
