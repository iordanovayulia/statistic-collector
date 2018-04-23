package testApp.start;

import testApp.logic.StatisticCollector;
import testApp.persistance.StatisticDAO;
import testApp.utils.DatabaseConnector;
import testApp.utils.FileProcessor;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;



public class Main {


    public static void main(String[] args) {

        File path  = new File("D:\\testDir");

        if(path.exists()) {

            FileProcessor fileProcessor = new FileProcessor();
            StatisticDAO statisticDao = new StatisticDAO();
            StatisticCollector statisticCollector = new StatisticCollector(fileProcessor, statisticDao);
            DatabaseConnector connector = new DatabaseConnector("jdbc:mysql://localhost:3306/file_statistic", "root", "root");

            List<File> files = fileProcessor.fileIndex(path);
            statisticDao.setConnection(connector.connect());

            ExecutorService service = Executors.newCachedThreadPool();
            CountDownLatch stopMain = new CountDownLatch(files.size());

            for (File file : files) {
                service.execute(() -> statisticCollector.handleFileContent(file, stopMain));
            }

            while (stopMain.getCount() > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            service.shutdown();
            connector.closeConnection();
        }
    }

}
