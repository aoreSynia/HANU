package a1_2101140062;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Query {

    private final List<Word> keyWords = new ArrayList<>();

    /**A constructor which receives the raw search phrase from user, then extract only
     keywords from it. */
    public Query(String searchPhrase) {
        Scanner scanner = new Scanner(searchPhrase);
        while (scanner.hasNext()) {
            Word newWord = Word.createWord(scanner.next());
            if (isKeyWord(newWord)) {
                keyWords.add(newWord);
            }
        }
    }

    boolean isKeyWord(Word word) {
        return (word.isKeyword());
    }

    /**
     * Returns a list of the query’s keywords in the same order as they appear in the
     * raw search phrase.
     */
    public List<Word> getKeywords() {
        return keyWords;
    }

    /**
     * Returns a list of matches against the input document. Sort matches by position
     * where the keyword first appears in the document. See the Engine.Match class for more
     * information about search matches.
     */
    public List<Match> matchAgainst(Doc d) {
        List<Match> matches = keyWords.stream()
            .filter(keyword -> d != null && d.getAllWords().contains(keyword))
            .map(keyword -> {
                int freq = 0, firstIndex = 0;
                for (int j = 0; j < d.getAllWords().size(); j++) {
                    if (d.getAllWords().get(j).equals(keyword)) {
                        freq++;
                        firstIndex = j;
                    }
                }
                return new Match(d, keyword, freq, firstIndex);
            })
            .filter(match -> match.getFreq() != 0 || match.getFirstIndex() != 0)
            .sorted(Match::compareTo)
            .collect(Collectors.toList());
        return matches;
    }


}