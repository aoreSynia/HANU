package a1_2101140062;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Engine {
    private List<Doc> documents = new ArrayList<>();

    public int loadDocs(String dirname) {
        // Initialize a counter for loaded documents.
        int loadedDocs = 0;

        // Get a list of files in the specified directory.
        File folder = new File(dirname);
        File[] files = folder.listFiles();

        if (files != null) {
            // Iterate through the files and load each as a document.
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    try {
                        String content = readTextFile(file.getPath());
                        Doc doc = new Doc(content);
                        documents.add(doc);
                        loadedDocs++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return loadedDocs;
    }

    public Doc[] getDocs() {
        return documents.toArray(new Doc[0]);
    }

    public List<Result> search(Query q) {
        List<Result> results = new ArrayList<>();

        // Iterate through the loaded documents and match the query against each document.
        for (Doc doc : documents) {
            List<Match> matches = q.matchAgainst(doc);
            if (!matches.isEmpty()) {
                Result result = new Result(doc, matches);
                results.add(result);
            }
        }

        // Sort the results using the compareTo() method of the Result class.
        results.sort(Result::compareTo);

        return results;
    }

    public String htmlResult(List<Result> results) {
        StringBuilder html = new StringBuilder();

        // Iterate through the sorted results and generate HTML output.
        for (Result result : results) {
            html.append(result.htmlHighlight());
        }

        return html.toString();
    }

    // Helper method to read the content of a text file.
    private String readTextFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
