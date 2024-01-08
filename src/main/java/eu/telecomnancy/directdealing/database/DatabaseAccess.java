package eu.telecomnancy.directdealing.database;

import java.sql.*;

public class DatabaseAccess {
    static Connection connection;

    private DatabaseAccess() throws SQLException {
        connectToDatabase();
    }

    public static void connectToDatabase() throws SQLException {
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:"+"src/main/resources/eu/telecomnancy/directdealing/database/database.db");
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
    }


    public static void disconnectFromDatabase() throws SQLException {
        if (connection != null)
            commitDatabase();
            connection.close();
    }
    public static void commitDatabase() throws SQLException {
        if (connection != null){
            connection.commit();
        }
    }
}

