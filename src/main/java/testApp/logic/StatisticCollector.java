package testApp.logic;

import testApp.persistance.StatisticDAO;
import testApp.persistance.StatisticStorage;
import testApp.utils.FileProcessor;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;



public class StatisticCollector {

    private FileProcessor fileProcessor;
    private StatisticDAO statisticDao;

    public StatisticCollector(FileProcessor fileProcessor, StatisticDAO statisticDao) {
        this.fileProcessor = fileProcessor;
        this.statisticDao = statisticDao;
    }


    public void handleFileContent(List<File> files) {

        for (File filePath : files) {
            fileProcessor.setFilePath(filePath);
            List<StatisticStorage> linesStatistic = countStatistic();
            if (linesStatistic != null && linesStatistic.size() > 0) {
                statisticDao.persistStatistic(filePath, linesStatistic);
            }
        }
    }

    public synchronized void handleFileContent(File file, CountDownLatch latch) {

        fileProcessor.setFilePath(file);
        List<StatisticStorage> linesStatistic = countStatistic();
        if (linesStatistic != null && linesStatistic.size() > 0) {
            statisticDao.persistStatistic(file, linesStatistic);
        }
        latch.countDown();
    }

    private synchronized List<StatisticStorage> countStatistic() {

        List<StatisticStorage> linesStatistic = new LinkedList<>();
        String fileContent = fileProcessor.getFileContent();

        if (fileContent.length() > 0) {
            String pattern = System.getProperty("line.separator");
            List<String> lines = fileProcessor.splitContent(fileContent, pattern);

            for (String line : lines) {
                StatisticStorage statisticStorage = new StatisticStorage(line, fileProcessor);
                linesStatistic.add(statisticStorage);
            }
        }
        return linesStatistic;
    }

}
