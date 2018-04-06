package luxoft.test;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileProcessor {

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public StringBuilder getFileContent(){

        StringBuilder fileContent = new StringBuilder();
        File processedFile = new File(path);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(processedFile)))){

            int nextByte;
            while ((nextByte = reader.read()) != -1)
                fileContent.append((char)nextByte);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return fileContent;
    }

    public List<String> contentSplitter(String content, String pattern){

        List<String> listOfLines = new LinkedList<>();

        String[]arrayOgLines =  content.split(pattern);

        for(String line: arrayOgLines){
            if(line.trim().length() > 0)
                listOfLines.add(line);
        }
        return listOfLines;
    }




}
