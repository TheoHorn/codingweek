package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Account;
import eu.telecomnancy.directdealing.model.Admin;
import eu.telecomnancy.directdealing.model.User;

import java.sql.*;

public class AccountManager {
    public void addUser(User user) throws SQLException {
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return; // or throw an exception if needed
        }

        try (Statement statement = DatabaseAccess.connection.createStatement()) {

            // Insert new user into the ACCOUNT table
            String query = "INSERT INTO ACCOUNT (mail, lastname, firstname, balance, sleep, type, password) VALUES (?, ?, ?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
                // Set parameters for the prepared statement
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getFirstName());
                preparedStatement.setDouble(4, user.getBalance());
                preparedStatement.setBoolean(5, user.isSleeping());
                preparedStatement.setInt(6, 1);
                preparedStatement.setString(7, " ");

                // Execute the insertion query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
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

    public Account getAccount(String mail) throws SQLException {
        // getting account from mail primary key
        String query = "SELECT * FROM ACCOUNT WHERE mail = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // recup des infos
                String mail1 = resultSet.getString("mail");
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                double credit = resultSet.getDouble("balance");
                boolean sleep = resultSet.getBoolean("sleep");
                int type = resultSet.getInt("type");
                String password = resultSet.getString("password");

                // creation de l'objet
                if (type == 1) {
                    return new User(lastname, firstname, mail1, credit, sleep);
                } else if (type == 2) {
                    return new Admin(lastname, firstname, mail1);
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }

    public boolean isSave(String mail) throws SQLException {
        String query = "SELECT * FROM ACCOUNT WHERE mail = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }



}
