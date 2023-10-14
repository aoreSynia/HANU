package a1_2101140062;

import java.io.*;
import java.util.*;

public class Word {

    private final String prefix;

    private final String text;

    private final String suffix;

    /**Default constructor for testing */
    public Word (String pre, String txt, String suf) {
        this.prefix = pre;
        this.suffix = suf;
        this.text = txt;
    }
    /**
     * A set of stop words loaded by the loadStopWords() method.
     */
    public static Set<String> stopWords;

    /**
     * Returns true if the word is a keyword.
     */
    boolean isKeyword() {


        if(text.isEmpty()) {
            return false;
        }

        if (stopWords.contains(text.toLowerCase())) {
            return false;
        }


        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) <= '9' && text.charAt(i) >= '0') {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the prefix part of the word.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Returns the suffix part of the word.
     */
    public String getSuffix() {
        return this.suffix;
    }



    /**
     * Returns the text part of the word.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Two words are considered equal if their text parts are equal, case-insensitively.
     */
    public boolean equals(Object o) {
        if (o instanceof Word) {
            Word w = (Word) o;
            return this.getText().toLowerCase().equals(w.getText().toLowerCase());
        }
        return false;
    }

    /**
     * Returns the raw text of the word.
     */
    public String toString() {
        return getPrefix() + getText() + getSuffix();
    }

    /**
     * Construct and return a complete Engine.Word object from raw text.
     */
    public static Word createWord(String rawText) {
        String prefix = "";
        String text = "";
        String suffix = "";
        int i;
        for (i = 0; i < rawText.length(); i++) {
            // This character is not an alphabet letter
            if (!isALetter(rawText.charAt(i))) {
                prefix += rawText.charAt(i);
            } else {
                break;
            }
        }
    
        int j;
        for (j = i; j < rawText.length(); j++) {
            if (isALetter(rawText.charAt(j))) {
                if ((rawText.charAt(j) == ',' && j > 1)) {
                    break;
                } else if (rawText.charAt(j) == '.' &&
                        (rawText.charAt(j - 1) > '9' || rawText.charAt(j - 1) < '0') &&
                        (rawText.length() - j < 3)) {
                    break;
                } else {
                    text += rawText.charAt(j);
                }
            } else {
                break;
            }
        }
    
        int k;
        for (k = j; k < rawText.length(); k++) {
            suffix += rawText.charAt(k);
        }
    
        // Handle the case where "suffix" contains "'s" and then a letter or number
        if (suffix.startsWith("'s") && suffix.length() > 2 && isALetter(suffix.charAt(2))) {
            text += suffix;
            suffix = "";
        }
    
        return new Word(prefix, text, suffix);
    }
    


    public static boolean isALetter(char i) {
        if ((i <= 'z' && i >= 'a') || (i <= 'Z' && i >= 'A') || (i == ',') ||
                (i == '.') || (i == '-') || ('0' <= i && '9' >= i)) {
            return true;
        }
        return false;
    }
    /**
     * Load stop words into the set Engine.Word.stopWords from the text file whose name is
     * specified by fileName (which resides under the project’s root directory). This
     * method returns true if the task is completed successfully and false otherwise.
     */
    public static boolean loadStopWords(String fileName) throws FileNotFoundException{
        stopWords = new HashSet<>();
        try {
            File file = new File(fileName);
            Scanner dectector = new Scanner(file);

            while (dectector.hasNext()) {
                String word = dectector.next();
                stopWords.add(word);
            }
            dectector.close();
        } catch (FileNotFoundException e) {

        }
        if (stopWords.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

}

