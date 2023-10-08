package a1_2101140062;

import java.util.List;

public class Result implements Comparable<Result> {
    private Doc document;
    private List<Match> matches;

    public Result(Doc document, List<Match> matches) {
        this.document = document;
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public Doc getDoc() {
        return document;
    }

    public int getTotalFrequency() {
        int totalFrequency = 0;
        for (Match match : matches) {
            totalFrequency += match.getFreq();
        }
        return totalFrequency;
    }

    public double getAverageFirstIndex() {
        if (matches.isEmpty()) {
            return 0.0; // Avoid division by zero.
        }

        int totalFirstIndex = 0;
        for (Match match : matches) {
            totalFirstIndex += match.getFirstIndex();
        }

        return (double) totalFirstIndex / matches.size();
    }

    public String htmlHighlight() {
        // Generate HTML highlighting for the matches in the document's title and body.
        StringBuilder html = new StringBuilder();

        List<Word> title = document.getTitle();
        List<Word> body = document.getBody();

        for (Match match : matches) {
            Word keyword = match.getWord();
            int firstIndex = match.getFirstIndex();

            if (firstIndex < title.size()) {
                // Match found in the title.
                titleHighlight(html, title, keyword, firstIndex);
            } else {
                // Match found in the body.
                bodyHighlight(html, body, keyword, firstIndex - title.size());
            }
        }

        return html.toString();
    }

    private void titleHighlight(StringBuilder html, List<Word> title, Word keyword, int firstIndex) {
        // Add HTML highlighting for title.
        for (int i = 0; i < title.size(); i++) {
            Word word = title.get(i);
            if (i == firstIndex) {
                html.append("<u>").append(word.getText()).append("</u> ");
            } else {
                html.append(word.getText()).append(" ");
            }
        }
    }

    private void bodyHighlight(StringBuilder html, List<Word> body, Word keyword, int firstIndex) {
        // Add HTML highlighting for body.
        for (int i = 0; i < body.size(); i++) {
            Word word = body.get(i);
            if (i == firstIndex) {
                html.append("<b>").append(word.getText()).append("</b> ");
            } else {
                html.append(word.getText()).append(" ");
            }
        }
    }

    @Override
    public int compareTo(Result o) {
        // Compare results based on match count, total frequency, and average first index.
        if (matches.size() != o.matches.size()) {
            return Integer.compare(o.matches.size(), matches.size()); // Compare by match count in descending order.
        } else if (getTotalFrequency() != o.getTotalFrequency()) {
            return Integer.compare(o.getTotalFrequency(), getTotalFrequency()); // Compare by total frequency in descending order.
        } else {
            return Double.compare(getAverageFirstIndex(), o.getAverageFirstIndex()); // Compare by average first index in ascending order.
        }
    }
}
