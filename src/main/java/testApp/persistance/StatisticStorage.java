package testApp.persistance;

import testApp.logic.StatisticCounter;
import testApp.utils.FileProcessor;

import java.math.BigDecimal;
import java.util.List;



public class StatisticStorage {

    private String line;
    private FileProcessor fileProcessor;

    private String longestWord;
    private String shortestWord;
    private Integer lineLength;
    private BigDecimal averageWordLength;


    public StatisticStorage(String line, FileProcessor fileProcessor) {
        this.line = line;
        this.fileProcessor = fileProcessor;
        countValues();
    }

    public String getLine() {
        return line;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public String getShortestWord() {
        return shortestWord;
    }

    public Integer getLineLength() {
        return lineLength;
    }

    public BigDecimal getAverageWordLength() {
        return averageWordLength;
    }

    private void countValues(){

        List<String> words = fileProcessor.splitContent(line, " ");
        averageWordLength = StatisticCounter.averageWordLength(words);
        lineLength = StatisticCounter.lineLength(line);
        longestWord = StatisticCounter.longestWord(words);
        shortestWord = StatisticCounter.shortestWord(words);

    }

    @Override
    public String toString(){
        return "\nLONGEST WORD: " + longestWord + "\t\tSHORTEST WORD: " + shortestWord + "\n" +
                "LINE LENGTH: " + lineLength + "\t\tAWERAGE WORD LENGTH: " + averageWordLength;
    }
}
