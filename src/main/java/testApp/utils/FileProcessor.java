package testApp.utils;

import java.io.*;
import java.util.*;

public class FileProcessor {

    private File filePath;


    public synchronized void setFilePath(File filePath) {
        this.filePath = filePath;
    }

    public synchronized String getFileContent(){

        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){

            int nextByte;
            while ((nextByte = reader.read()) != -1) {
                fileContent.append((char) nextByte);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent.toString().trim();
    }

    public synchronized List<String> splitContent(String content, String pattern){

        List<String> listOfLines = new LinkedList<>();

        String[]arrayOfLines =  content.split(pattern);

        for(String line: arrayOfLines){
            if(line.trim().length() > 0) {
                listOfLines.add(line);
            }
        }
        return listOfLines;
    }

    public List<File> fileIndex(File item){

        Deque<File> dirsToVisit = new LinkedList<>();
        List<File> files = new LinkedList<>();

        if(item.isFile()){
            files.add(item);
        } else {
            dirsToVisit.add(item);
        }

        while (dirsToVisit.size() > 0) {
            item = dirsToVisit.removeFirst();

            if (item.isFile() && item.exists()) {
                files.add(item);

            } else if (item.isDirectory() && item.exists()) {

                File[] innerDirContent = item.listFiles();

                if (innerDirContent != null && innerDirContent.length > 0) {

                    List<File> innerDirContentAsList = Arrays.asList(innerDirContent);
                    dirsToVisit.addAll(innerDirContentAsList);
                }
            }
        }
        return files;
    }

    public String getCurrentFileName(){

        return filePath.getName();
    }

    public String getCurrentDirName(){

        return filePath.getParent();
    }


}
