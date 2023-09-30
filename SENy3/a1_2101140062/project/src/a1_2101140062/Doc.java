package a1_2101140062;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> title = new ArrayList<Word>();
    private List<Word> body = new ArrayList<Word>();

    public Doc(String content) {
        String[] lines = content.split("\n");

        boolean isTitle = true;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                isTitle = false;
                continue;
            }

            List<Word> words = splitTextIntoWords(line);

            if (isTitle) {
                title.addAll(words);
            } else {
                body.addAll(words);
            }
        }
    }

    public List<Word> getTitle() {
        return title;
    }

    public List<Word> getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doc otherDoc = (Doc) o;

        return title.equals(otherDoc.title) && body.equals(otherDoc.body);
    }

    // Helper method to split a line of text into words (using space as a delimiter)
    private List<Word> splitTextIntoWords(String text) {
        String[] wordsArray = text.split(" ");
        List<Word> wordsList = new ArrayList<>();

        for (String wordText : wordsArray) {
            wordsList.add(Word.createWord(wordText));
        }

        return wordsList;
    }
}
