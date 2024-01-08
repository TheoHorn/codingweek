package eu.telecomnancy.directdealing.database;

import java.sql.*;

public class DatabaseAccess {
    static Connection connection;

    private DatabaseAccess() throws SQLException {
        connectToDatabase();
    }

    public static void connectToDatabase() throws SQLException {
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:"+DatabaseAccess.class.getResource("/eu/telecomnancy/directdealing/database/database.db"));
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        String query1 = "SELECT tbl_name FROM sqlite_master;";
        ResultSet resultSet = null;
        try (Statement statement1 = DatabaseAccess.connection.createStatement()){
            statement1.execute(query1);
            resultSet = statement1.getResultSet();
        }
        System.out.println("heloo");
        while (resultSet.next()) {
            String column1 = resultSet.getString("tbl_name"); // Replace "column1" with the actual column name
            System.out.println("Column 1: " + column1);
        }

    }


    public static void disconnectFromDatabase() throws SQLException {
        if (connection != null)
            connection.close();
    }
}

