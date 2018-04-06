package luxoft.test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static FileProcessor fileProcessor = new FileProcessor();
    static StatisticCollector statisticCollector = new StatisticCollector();

    public static void main(String[] args) {

        LinkedList<String> dirsToVisit = new LinkedList<>();

        String fileName = "dir";
        dirsToVisit.add(fileName);

        while (dirsToVisit.size() > 0) {
            System.out.println(dirsToVisit);

            String itemPath = dirsToVisit.removeFirst();
            File item = new File(itemPath);
            System.out.println("itempath: " + itemPath);

            if (item.isFile() && item.exists()) {
                statistic(item);
            } else if (item.isDirectory() && item.exists()) {

                System.out.println(item.isDirectory());
                String[] innerDirContent = item.list();
                if (innerDirContent != null && innerDirContent.length > 0) {

                    List<String> innerContentToAdd = Arrays.asList(innerDirContent);
                    dirsToVisit.addAll(innerContentToAdd);
                }
            }
        }
    }

    public static void statistic(File item) {

        String dirName = item.getParent();
        String fileName = item.getName();
        fileProcessor.setPath(fileName);
        StringBuilder content = fileProcessor.getFileContent();
        String pattern = System.getProperty("line.separator");

        List<String> lines = fileProcessor.contentSplitter(content.toString(), pattern);

        for (String line : lines) {
            int lineLength = line.length();

            pattern = " ";
            List<String> words = fileProcessor.contentSplitter(line, pattern);
            int averageWordLength = statisticCollector.averageWordLength(words);

            String longestWord = statisticCollector.longestWord(words);
            String shortestWord = statisticCollector.shortestWord(words);

            System.out.println("dir: " + dirName + "\t\tfile: " + fileName);
            System.out.println("line length: " + lineLength + "\t\taverage length: " + averageWordLength);
            System.out.println("longest: " + longestWord + "\t\tshortest: " + shortestWord);
        }
    }


}
