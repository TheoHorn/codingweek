package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.Admin;
import eu.telecomnancy.directdealing.model.account.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountDAO is the class that allows to access the ACCOUNT table in the database
 */
public class AccountDAO {

    /**
     * save method allows to save an account in the database
     * @param account account to save
     * @return true if the account has been saved, false if not
     * @throws Exception if the connection is not open
     */
    public boolean save(Account account) throws Exception {
        // check if connection is open
        if (DatabaseAccess.connection == null) {
            throw new Exception("Veuillez créer ou ouvrir une base de données (Fichier)");
        }
        // check if account already exists
        String query = "SELECT * FROM ACCOUNT WHERE mail = ?";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, account.getEmail());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results

                // update account
                String queryUpdate = "UPDATE ACCOUNT SET lastname = ?, firstname = ?, balance = ?, sleep = ?, type = ?, password = ?, city = ?, address = ? WHERE mail = ?";
                try (PreparedStatement preparedStatementUpdate = DatabaseAccess.connection.prepareStatement(queryUpdate)) {
                    // Set parameters for the prepared statement
                    preparedStatementUpdate.setString(1, account.getLastName());
                    preparedStatementUpdate.setString(2, account.getFirstName());
                    preparedStatementUpdate.setDouble(3, account.getBalance());
                    preparedStatementUpdate.setBoolean(4, account.isSleeping());
                    preparedStatementUpdate.setInt(5, 1);
                    preparedStatementUpdate.setString(6, account.getPassword());
                    preparedStatementUpdate.setString(7, account.getCity());
                    preparedStatementUpdate.setString(8, account.getAddress());
                    preparedStatementUpdate.setString(9, account.getEmail());

                    // Execute the updated query
                    preparedStatementUpdate.executeUpdate();
                    return true;
                }
            } else {
                // account doesn't exist
                // Insert new user into the ACCOUNT table

                String queryInsert = "INSERT INTO ACCOUNT (mail, lastname, firstname, balance, sleep, type, password, city, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
                try (PreparedStatement preparedStatementInsert = DatabaseAccess.connection.prepareStatement(queryInsert)) {
                    // Set parameters for the prepared statement
                    preparedStatementInsert.setString(1, account.getEmail());
                    preparedStatementInsert.setString(2, account.getLastName());
                    preparedStatementInsert.setString(3, account.getFirstName());
                    preparedStatementInsert.setDouble(4, account.getBalance());
                    preparedStatementInsert.setBoolean(5, account.isSleeping());
                    preparedStatementInsert.setInt(6, 1);
                    preparedStatementInsert.setString(7, account.getPassword());
                    preparedStatementInsert.setString(8,account.getCity());
                    preparedStatementInsert.setString(9,account.getAddress());

                    // Execute the insertion query
                    preparedStatementInsert.executeUpdate();
                    return true;
            }
        }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    /**
     * get method allows to get an account from the database
     * @param mail mail of the account to get
     * @return the account if it exists, null if not
     * @throws SQLException if the connection is not open
     */
    public Account get(String mail) throws SQLException {
        // check if connection is open
        if (DatabaseAccess.connection == null) {
            throw new SQLException("Veuillez créer ou ouvrir une base de données (Fichier)");
        }
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
                String city = resultSet.getString("city");
                String address = resultSet.getString("address");
                // creation de l'objet
                if (type == 1) {
                    return new User(lastname, firstname, mail1, credit, sleep, password, city, address);
                } else if (type == 2) {
                    return new Admin(lastname, firstname, mail1, password, city, address);
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

    public List<User> getUsers() throws SQLException {
        // getting offers from idContent primary key
        String query = "SELECT * FROM ACCOUNT";
        ResultSet resultSet = null;

        List<User> users = new ArrayList<User>();


        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results

                // recup des infos
                String mail1 = resultSet.getString("mail");
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                double credit = resultSet.getDouble("balance");
                boolean sleep = resultSet.getBoolean("sleep");
                String password = resultSet.getString("password");
                String city = resultSet.getString("city");
                String address = resultSet.getString("address");

                // creation de l'objet
                User user = new User(lastname, firstname, mail1, credit, sleep, password, city, address);
                users.add(user);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return users;
    }

    public void delete(String mail) throws SQLException {

        String query = "DELETE FROM ACCOUNT WHERE mail = ?;";

        PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query);
        preparedStatement.setString(1, mail);
        preparedStatement.execute();
    }
}
