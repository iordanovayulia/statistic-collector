package testApp.persistance;

import java.io.File;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;



public class StatisticDAO {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void persistStatistic(File file, List<StatisticStorage> list){

        updateFiles(file.getName());
        for(StatisticStorage statisticStorage : list) {

            updateContent(statisticStorage.getLine());
            updateStatistic(statisticStorage.getLineLength(), statisticStorage.getAverageWordLength(), statisticStorage.getLongestWord(), statisticStorage.getShortestWord());
        }
    }

    private void updateStatistic(int lineLength, BigDecimal averageWordLength, String longestWord, String shortestWord) {

        String query = "INSERT INTO details  VALUES (?, ?, ?, ?, ?);";
        int lineId = getNextId("line_id", "content");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, lineId);
            preparedStatement.setInt(2, lineLength);
            preparedStatement.setBigDecimal(3, averageWordLength);
            preparedStatement.setString(4, longestWord);
            preparedStatement.setString(5, shortestWord);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateContent(String line) {

        String query = "INSERT INTO content(file_id, line_content) VALUES (?, ?);";
        int fileId = getNextId("file_id", "files");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, fileId);
            preparedStatement.setString(2, line);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateFiles(String fileName) {

        String query = "INSERT INTO files (file_name) VALUES (?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, fileName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNextId(String columnName, String tableName) {

        int id = 0;
        String query = "SELECT  MAX(" + columnName +") FROM "+ tableName + ";";

        try(Statement statement = connection.createStatement();
            ResultSet result    = statement.executeQuery(query)) {

            while (result.next()){
                id = result.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}




