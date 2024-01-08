package eu.telecomnancy.directdealing.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {

    Connection connection = null;
    public void connectToDatabase() throws SQLException {
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/theot/Documents/TELECOM/2A/codingweek-12/src/main/resources/eu/telecomnancy/directdealing/database/database.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
    }

    public void disconnectFromDatabase() throws SQLException {
        if (connection != null)
            connection.close();
    }

    public static void main(String[] args) {
        Application app = new Application();
        try {
            app.connectToDatabase();
            app.disconnectFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
