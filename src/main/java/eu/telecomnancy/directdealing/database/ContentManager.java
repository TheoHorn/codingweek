package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.content.Equipment;
import eu.telecomnancy.directdealing.model.content.Services;
import javafx.scene.image.Image;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContentManager {
    public static void addContent(Content content) throws SQLException {
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return; // or throw an exception if needed
        }

        try (Statement statement = DatabaseAccess.connection.createStatement()) {

            // Insert new user into the ACCOUNT table
            String query = "INSERT INTO CONTENT (idContent, title, category, description, image, price, isEquipment, localisation) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
                // Set parameters for the prepared statement
                preparedStatement.setInt(1, content.getId());
                preparedStatement.setString(2, content.getTitle());
                preparedStatement.setString(3, content.getCategory());
                preparedStatement.setString(4, content.getDescription());
                preparedStatement.setObject(5, content.getImage());
                preparedStatement.setInt(6, content.getPrice());
                preparedStatement.setBoolean(7, content.isEquipement());
                preparedStatement.setString(8, content.getLocalisation());

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
                boolean isEquipment = resultSet.getBoolean("isEquipment");
                String localisation = resultSet.getString("localisation");

                // creation de l'objet
                if (isEquipment) {
                    return new Equipment(title, category, description, image) {
                    };
                } else {
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
}
