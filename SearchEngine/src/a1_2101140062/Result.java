
package a1_2101140062;

import java.util.ArrayList;
import java.util.List;

public class Result implements Comparable<Result> {

    private final List<Match> matchList;
    private final Doc doc;

    public Result(Doc doc, List<Match> matchList) {
        this.doc = doc;
        this.matchList = matchList;
    }

    public List<Match> getMatches() {
        return matchList;
    }

    public int getTotalFrequency() {
        return matchList.isEmpty() ? 0 : matchList.get(0).getFreq();
    }

    public double getAverageFirstIndex() {
        double sumFirstIndex = matchList.stream().mapToDouble(Match::getFirstIndex).sum();
        return matchList.isEmpty() ? 0.0 : sumFirstIndex / matchList.size();
    }

    public String htmlHighlight() {
        StringBuilder htmlTitle = new StringBuilder();
        StringBuilder htmlBody = new StringBuilder();
        List<Word> list = new ArrayList<>();

        for (Match match : matchList) {
            list.add(match.getWord());
        }

        for (int i = 0; i < doc.titleLength(); i++) {
            Word word = doc.getAllWords().get(i);
            String formattedWord = formatWord(word, list, "<u>", "</u>");
            htmlTitle.append(formattedWord).append(" ");
        }

        for (int i = doc.titleLength(); i < doc.getAllWords().size(); i++) {
            Word word = doc.getAllWords().get(i);
            String formattedWord = formatWord(word, list, "<b>", "</b>");
            htmlBody.append(formattedWord).append(" ");
        }

        String titleHtml = "<h3>" + htmlTitle.toString().trim() + "</h3>";
        String bodyHtml = "<p>" + htmlBody.toString().trim() + "</p>";

        return titleHtml + bodyHtml;
    }

    private String formatWord(Word word, List<Word> matchList, String openTag, String closeTag) {
        if (matchList.contains(word)) {
            return word.getPrefix() + openTag + word.getText() + closeTag + word.getSuffix();
        } else {
            return word.getPrefix() + word.getText() + word.getSuffix();
        }
    }


    @Override
    public int compareTo(Result o) {
        if (this.getMatches().size() > o.getMatches().size()) {
            return -1;
        } else if (this.getMatches().size() < o.getMatches().size()) {
            return 1;
        } else if (this.getTotalFrequency() > o.getTotalFrequency()) {
            return -2;
        } else if (this.getTotalFrequency() < o.getTotalFrequency()) {
            return 2;
        } else {
            return 0;
        }

    }

    public Doc getDoc() {
        return doc;
    }
}
