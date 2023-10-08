package a1_2101140062;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class Word {
    // Public attributes
    public static Set<String> stopWords = new HashSet<>();

    // Instance variables
    private String prefix;
    private String text;
    private String suffix;

    // Constructor
    public Word(String prefix, String text, String suffix) {
        this.prefix = prefix;
        this.text = text;
        this.suffix = suffix;
    }

    // Public methods
    public boolean isKeyword() {
        String lowerText = text.toLowerCase();
    
        // Check if the word is in the stopWords set
        if (stopWords.contains(lowerText)) {
            return false; // It's a stop word, so not a keyword
        }
    
        // Check if the word has a non-alphanumeric prefix or suffix
        if (!lowerText.matches("^[a-zA-Z0-9]*$") || lowerText.trim().isEmpty() || lowerText.startsWith(" ") || lowerText.endsWith(" ")) {
            return false; // It has a non-alphanumeric prefix or suffix, or it's empty or contains spaces, so not a keyword
        }
    
        // Check if the word contains spaces
        if (lowerText.contains(" ")) {
            return false; // It contains spaces, so not a keyword
        }
    
        return true; // It's a keyword
    }
    

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return text != null ? text.equalsIgnoreCase(word.text) : word.text == null;
    }

    @Override
    public String toString() {
        return prefix + text + suffix;
    }

    // Public static methods
    public static Word createWord(String rawText){

        String prefix = "";
        String text = "";
        String suffix = "";
        
        boolean isTextStarted = false; // Sử dụng cờ để xác định khi nào text bắt đầu

        for (int i = 0; i < rawText.length(); i++) {
            char character = rawText.charAt(i);
            if (!Character.toString(character).matches("[a-zA-Z]")) {
                if (!isTextStarted) {
                    prefix += character;
                } else {
                    suffix += character;
                }
            } else {
                isTextStarted = true;
                text += character;
            }
        }
        // for (int i = 0; i < rawText.length(); i++) {
        //     char character = rawText.charAt(i);
        //     if (Character.toString(character).matches("[0-9]") && Character.toString(character).matches(".*[,.@#$%].*")) {
        //         text += character;
        //     }
        // }
        // prefix = "";
        // suffix = "";

        if (rawText.endsWith("'s")) {
            // Handle's as part of the suffix
            int index = rawText.lastIndexOf("'s");
            if (index > 0) {
                prefix = "";
                // Xoá 's khỏi rawtext sau đó thêm vào trong suffix 
                text = rawText.substring(0, index);
                suffix = rawText.substring(index);
            }
        }
        
        if (rawText.contains(" ") || rawText.matches("0-9")) {
            prefix = "";
            text = rawText;
            suffix = "";
        }

        // trả lại 1 word mới
        return new Word(prefix, text, suffix);

    }
    

    public static boolean loadStopWords(String fileName) {
        // Load stop words from the specified file into the 'stopWords' set.
        try {
            File file = new File(fileName);
            if (file.exists()) {
                stopWords.clear();  // Clear the existing stop words.
                Files.lines(file.toPath())
                     .map(String::toLowerCase)
                     .forEach(stopWords::add);
                return true; // Loading successful.
            } else {
                return false; // File does not exist.
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error occurred while reading the file.
        }
    }
}
