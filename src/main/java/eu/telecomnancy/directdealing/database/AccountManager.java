package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.Admin;
import eu.telecomnancy.directdealing.model.account.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

import static eu.telecomnancy.directdealing.database.ReallyStrongSecuredPassword.validatePassword;

public class AccountManager {

    public static void addUser(User user) throws SQLException {
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
                preparedStatement.setString(7, user.getPassword());

                // Execute the insertion query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
    }

    public static boolean updateAccountInfo(Account account) throws SQLException {
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return false; // or throw an exception if needed
        }

        String query = "UPDATE ACCOUNT SET lastname = ?, firstname = ? WHERE mail = ?";

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, account.getLastName());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(5, account.getPassword());

        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean updateAccountPassword(Account account) throws SQLException{
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return false; // or throw an exception if needed
        }

        String query = "UPDATE ACCOUNT SET password = ? WHERE mail = ?";

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getEmail());

        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void addAdmin(Admin admin) throws SQLException {
        // adding account to the database
        String query = "INSERT INTO ACCOUNT (id, mail, lastname, firstname, credit, sleep, type, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            // Préparation de la requête
            preparedStatement.setInt(1, admin.getId());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getLastName());
            preparedStatement.setString(4,admin.getFirstName());
            preparedStatement.setFloat(5, 0);
            preparedStatement.setBoolean(6, false);
            preparedStatement.setInt(7,2);
            preparedStatement.setString(8, admin.getPassword());

            // Exécuter la requête d'insertion
            preparedStatement.executeUpdate();
        }
    }

    public static Account getAccount(String mail) throws SQLException {
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

    public static Account getAccount(int id) throws SQLException {
        // getting account from mail primary key
        String query = "SELECT * FROM ACCOUNT WHERE mail = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
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

    public static boolean isSave(String mail) throws SQLException {
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

    public static Account login(String mail, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        String query = "SELECT password FROM ACCOUNT WHERE mail = ?";
        ResultSet resultSet = null;

        try(PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)){
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String passwordFromDatabase = resultSet.getString("password");
                System.out.println(passwordFromDatabase);
                System.out.println(password);
                boolean matched = validatePassword(password, passwordFromDatabase);
                if(matched){
                    System.out.println("Login successful");
                    return getAccount(mail);
                }else{
                    System.out.println("Login failed");
                    return null;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updatePasswordAccount(String old_password, String new_password, Account account) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (login(account.getEmail(), old_password) != null) {
            account.setPassword(new_password);
            return updateAccountPassword(account);
        }
        return false;
    }
}
