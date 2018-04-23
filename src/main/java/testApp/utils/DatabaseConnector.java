package testApp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnector {

    private Connection connection = null;
    private String database;
    private String username;
    private String password;


    public DatabaseConnector(String database, String username, String password) {
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public Connection connect(){
        try {
            connection = DriverManager.getConnection(database, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }

        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.commit();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}