package testApp.logic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;



public class StatisticCounter {

    public static BigDecimal averageWordLength(List<String> words){

        int wordsQuantity;

        if((wordsQuantity = words.size()) > 0) {

            double totalLength = 0;

            for (String word : words) {

                double wordLength = word.length();
                totalLength += wordLength;
            }

            return new BigDecimal(totalLength / wordsQuantity, new MathContext(3, RoundingMode.HALF_EVEN));
        }
        return BigDecimal.ZERO;
    }

    public static Integer lineLength(String line){
        return line.length();
    }

    public static String shortestWord(List<String> words){

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

    public static String longestWord(List<String> words){

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
