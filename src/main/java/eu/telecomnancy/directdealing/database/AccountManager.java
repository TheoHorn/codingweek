package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Account;
import eu.telecomnancy.directdealing.model.Admin;
import eu.telecomnancy.directdealing.model.User;

import java.sql.*;

public class AccountManager {
    public void addUser(User user) throws SQLException {
        // adding account to the database
        System.out.println(DatabaseAccess.connection);
        String query1 = "SELECT tbl_name FROM sqlite_master;";
        ResultSet resultSet = null;
        try (Statement statement = DatabaseAccess.connection.createStatement()){
             statement.execute(query1);
             resultSet = statement.getResultSet();
        }
        while (resultSet.next()) {
            String column1 = resultSet.getString("tbl_name"); // Replace "column1" with the actual column name
            System.out.println("Column 1: " + column1);
        }
        // System.out.println(resultSet);
        String query = "INSERT INTO ACCOUNT (mail, lastname, firstname, credit, sleep, type, password) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            // Préparation de la requête
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3,user.getFirstName());
            preparedStatement.setDouble(4, user.getBalance());
            preparedStatement.setBoolean(5, user.isSleeping());
            preparedStatement.setInt(6,1);
            preparedStatement.setString(7, " ");

            // Exécuter la requête d'insertion
            preparedStatement.executeUpdate();
        }
        catch (
                Exception e        )
        {
            e.printStackTrace();
        }
    }

    public void addAdmin(Admin admin) throws SQLException {
        // adding account to the database
        String query = "INSERT INTO ACCOUNT (mail, lastname, firstname, credit, sleep, type, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            // Préparation de la requête
            preparedStatement.setString(1, admin.getEmail());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3,admin.getFirstName());
            preparedStatement.setFloat(4, 0);
            preparedStatement.setBoolean(5, false);
            preparedStatement.setInt(6,2);
            preparedStatement.setString(7, " ");

            // Exécuter la requête d'insertion
            preparedStatement.executeUpdate();
        }
    }

    public Account getAccount(String mail){
        // getting account from mail primary key
        return null;
    }
}
