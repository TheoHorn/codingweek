package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.Admin;
import eu.telecomnancy.directdealing.model.account.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

import static eu.telecomnancy.directdealing.Main.app;
import static eu.telecomnancy.directdealing.database.ReallyStrongSecuredPassword.validatePassword;

public class AccountDAO {

    public boolean save(Account account) throws SQLException {
        // check if account already exists
        String query = "SELECT * FROM ACCOUNT WHERE mail = ?";
        ResultSet resultSet = null;
        boolean find = false;
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, account.getEmail());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                find = true;
                // update account
                String queryUpdate = "UPDATE ACCOUNT SET lastname = ?, firstname = ?, balance = ?, sleep = ?, type = ?, password = ? WHERE mail = ?";
                try (PreparedStatement preparedStatementUpdate = DatabaseAccess.connection.prepareStatement(queryUpdate)) {
                    // Set parameters for the prepared statement
                    preparedStatementUpdate.setString(1, account.getLastName());
                    preparedStatementUpdate.setString(2, account.getFirstName());
                    if (account.isAdministrator()) {
                        // if account is admin, balance and sleep are set to default values
                        preparedStatementUpdate.setDouble(3, 0);
                        preparedStatementUpdate.setBoolean(4, false);
                    } else {
                        // if account is user, balance and sleep are set to updated user values
                        preparedStatementUpdate.setDouble(3, ((User) account).getBalance());
                        preparedStatementUpdate.setBoolean(4, ((User) account).isSleeping());
                    }
                    preparedStatementUpdate.setInt(5, 1);
                    preparedStatementUpdate.setString(6, account.getPassword());
                    preparedStatementUpdate.setString(7, account.getEmail());
                    // Execute the updated query
                    preparedStatementUpdate.executeUpdate();
                    return true;
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        if (!find) {
            // account doesn't exist
            // Insert new user into the ACCOUNT table
            String queryInsert = "INSERT INTO ACCOUNT (mail, lastname, firstname, balance, sleep, type, password) VALUES (?, ?, ?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(queryInsert)) {
                // Set parameters for the prepared statement
                preparedStatement.setString(1, account.getEmail());
                preparedStatement.setString(2, account.getLastName());
                preparedStatement.setString(3, account.getFirstName());
                if (account.isAdministrator()) {
                    // if account is admin, balance and sleep are set to default values
                    preparedStatement.setDouble(4, 0);
                    preparedStatement.setBoolean(5, false);
                } else {
                    // if account is user, balance and sleep are set to updated user values
                    preparedStatement.setDouble(4, ((User) account).getBalance());
                    preparedStatement.setBoolean(5, ((User) account).isSleeping());
                }
                preparedStatement.setInt(6, 1);
                preparedStatement.setString(7, account.getPassword());

                // Execute the insertion query
                preparedStatement.executeUpdate();
                return true;

            } catch (SQLException e) {
                // Handle SQL exceptions
                e.printStackTrace();
            }
        }
        return false;
    }

    public Account get(String mail) throws SQLException {
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
                    return new User(lastname, firstname, mail1, credit, sleep, password);
                } else if (type == 2) {
                    return new Admin(lastname, firstname, mail1, password);
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
    }



}
