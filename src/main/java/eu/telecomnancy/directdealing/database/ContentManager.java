package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.*;
import javafx.scene.image.Image;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import static eu.telecomnancy.directdealing.database.ReallyStrongSecuredPassword.validatePassword;

public class ContentManager {
    public static void addContent(Content content) throws SQLException {
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return; // or throw an exception if needed
        }

        try (Statement statement = DatabaseAccess.connection.createStatement()) {

            // Insert new user into the ACCOUNT table
            String query = "INSERT INTO CONTENT (id, title, category, description, imageUrl) VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
                // Set parameters for the prepared statement
                preparedStatement.setInt(1, content.getId());
                preparedStatement.setString(2, content.getTitle());
                preparedStatement.setString(3, content.getCategory());
                preparedStatement.setString(4, content.getDescription());
                preparedStatement.setObject(5, content.getImage());
                preparedStatement.setInt(6, content.getType());

                // Execute the insertion query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
    }

    public static Content getContent(int id) throws SQLException {
        // getting account from mail primary key
        String query = "SELECT * FROM ACCOUNT WHERE id = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // recup des infos
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                Image image = (Image) resultSet.getObject("image");
                int type = resultSet.getInt("type");
                Date date = resultSet.getDate("returnDate");

                // creation de l'objet
                if (type == 1) {
                    return new Equipment(title, category, description, image, date) {
                    };
                } else if (type == 2) {
                    return new Services(title, category, description, image);
                }
            }
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
}
