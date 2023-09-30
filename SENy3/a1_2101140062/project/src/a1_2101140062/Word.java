package a1_2101140062;
    
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.HashSet;
    import java.util.Set;

    public class Word {
        // Attributes
        private String prefix;
        private String suffix;
        private String text;
        public static Set<String> stopWords = new HashSet<>();

        private Word(String prefix, String text, String suffix) {
            this.prefix = prefix;
            this.text = text;
            this.suffix = suffix;
        }
        
        public boolean isKeyword(){
            return !stopWords.contains(text.toLowerCase());
        }

        public String getPrefix(){
            return prefix;
        }

        // Returns the suffix part of the word.
        public String getSuffix(){
            return suffix;
        }
        
        // Returns the text part of the word.
        public String getText(){
            return text;
        }

        // Two words are considered equal if their text parts are equal, case-insensitively.\
        @Override
        public boolean equals(Object o){
            // check đối tượng so sánh và oj được truyền vào
            if(this == o) return true; 

            // điều kiện 2 : nếu o trong param mà = null hoặc class của o khác với this thì sẽ trả false
            if(o == null || getClass() != o.getClass()) return false;

            // điều kiện 3 : là so sánh về chữ không quan tâm những cái khác 
            Word otherWord = (Word) o;
            return getText().equalsIgnoreCase(otherWord.getText()); 
            // cụ thể hơn thì khi trả lại phương thực này thì sẽ ra đoạn code như sau 
            // word1.equals(o) == word1.getText..equalsIgnoreCase(o.getText());
        }

        // Returns the raw text of the word.    
        public String toString(){
            return prefix + text + suffix;
        }

        // Construct and return a complete Word object from raw text.    
        public static Word createWord(String rawText){
            
            String prefix = "";
            String text = rawText;
            String suffix = "";

            if (rawText.endsWith("'s")) {
                // Handle's as part of the suffix
                int index = rawText.lastIndexOf("'s");
                if (index > 0) {
                    // Xoá 's khỏi rawtext sau đó thêm vào trong suffix 
                    text = rawText.substring(0, index);
                    suffix = rawText.substring(index);
                }
            }

            // trả lại 1 word mới
            return new Word(prefix, text, suffix);

        }
        

        public static boolean loadStopWords(String fileName) {
            Set<String> stopWords = new HashSet<>();

            try {
                Path path = Paths.get(fileName);
                Files.lines(path).forEach(line -> stopWords.add(line.toLowerCase()));
                Word.stopWords = stopWords;
                return true;
            } catch (IOException e) {
                System.err.println("Error loading stop words: " + e.getMessage());
                return false;
            }
    }
    }


