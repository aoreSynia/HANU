package a1_2101140062;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private List<Word> keywords = new ArrayList<>();

    public Query(String searchPhrase) {
        // Extract keywords from the search phrase.
        String[] words = searchPhrase.split("\\s+");
        for (String word : words) {
            keywords.add(Word.createWord(word));
        }
    }

    public List<Word> getKeywords() {
        return keywords;
    }

    // Implement the matchAgainst() method as specified in the assignment.
    public List<Match> matchAgainst(Doc d) {
        List<Match> matches = new ArrayList<>();
        
        List<Word> title = d.getTitle();
        List<Word> body = d.getBody();
    
        // Iterate through each keyword in the query.
        for (Word keyword : keywords) {
            int keywordFrequency = 0;
            int firstIndex = -1;
    
            // Match keywords in the title.
            for (int i = 0; i < title.size(); i++) {
                Word titleWord = title.get(i);
                if (titleWord.equals(keyword)) {
                    keywordFrequency++;
                    if (firstIndex == -1) {
                        firstIndex = i;
                    }
                }
            }
    
            // Match keywords in the body.
            for (int i = 0; i < body.size(); i++) {
                Word bodyWord = body.get(i);
                if (bodyWord.equals(keyword)) {
                    keywordFrequency++;
                    if (firstIndex == -1) {
                        firstIndex = i + title.size(); // Adjust the index for the body.
                    }
                }
            }
    
            if (keywordFrequency > 0) {
                Match match = new Match(d, keyword, keywordFrequency, firstIndex);
                matches.add(match);
            }
        }
    
        return matches;
    }
    
}
