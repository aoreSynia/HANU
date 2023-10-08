package a1_2101140062;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> title;
    private List<Word> body;

    public Doc(String content) {
        // Split the content into title and body based on the assignment rules.
        String[] lines = content.split("\n");
        if (lines.length == 2) {
            title = parseText(lines[0]);
            body = parseText(lines[1]);
        } else {
            title = new ArrayList<>();
            body = new ArrayList<>();
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

        Doc doc = (Doc) o;

        return (title != null ? title.equals(doc.title) : doc.title == null) &&
               (body != null ? body.equals(doc.body) : doc.body == null);
    }

    // Helper method to parse a text into a list of Word objects
    private List<Word> parseText(String text) {
        String[] words = text.split("\\s+");
        List<Word> wordList = new ArrayList<>();
        for (String word : words) {
            wordList.add(Word.createWord(word));
        }
        return wordList;
    }
}
