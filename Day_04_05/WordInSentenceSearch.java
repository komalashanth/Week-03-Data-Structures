public class WordInSentenceSearch {
    public static String findSentence(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {
            "Java is a programming language.",
            "This is a sample sentence.",
            "We are learning data structures."
        };
        String result = findSentence(sentences, "sample");
        System.out.println("Result: " + result);
    }
}

