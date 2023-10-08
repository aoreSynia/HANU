package a1_2101140062;

public class Match {
    private Doc document;
    private Word word;
    private int frequency;
    private int firstIndex;

    public Match(Doc document, Word word, int frequency, int firstIndex) {
        this.document = document;
        this.word = word;
        this.frequency = frequency;
        this.firstIndex = firstIndex;
    }

    public int getFreq() {
        return frequency;
    }

    public int getFirstIndex() {
        return firstIndex;
    }
    
    public Doc getDocument() {
        return document;
    }

    public Word getWord() {
        return word;
    }
}
