package luxoft.test;

import java.util.List;

public class StatisticCollector {

    public int averageWordLength(List<String> words){

        int wordsQuantity;

        if((wordsQuantity = words.size()) > 0) {

            int totalLength = 0;

            for (String word : words) {

                int wordLength = word.length();
                totalLength += wordLength;
            }

            return totalLength / wordsQuantity;
        }
        return 0;
    }

    public String shortestWord(List<String> words){

        String shortestWord = "";

        int minLength = Integer.MAX_VALUE;
        for (String word : words) {

            int wordLength;

            if((wordLength = word.length()) < minLength){
                minLength = wordLength;
                shortestWord = word;
            }
        }
        return shortestWord;
    }

    public String longestWord(List<String> words){

        String longestWord = "";

        int maxLength = Integer.MIN_VALUE;

        for (String word : words) {

            int wordLength = word.length();
            if (wordLength > maxLength){
                maxLength = wordLength;
                longestWord = word;
            }

        }
        return longestWord;
    }

}
