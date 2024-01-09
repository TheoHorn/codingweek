package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.content.Equipment;
import eu.telecomnancy.directdealing.model.content.Service;
import javafx.scene.image.Image;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static eu.telecomnancy.directdealing.database.DatabaseAccess.connection;

public class ContentManager {
    public int addContent(Content content) throws SQLException {
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return 0;
        }

        try (Statement statement = DatabaseAccess.connection.createStatement()) {

            // Insert new user into the ACCOUNT table
            String query = "INSERT INTO CONTENT (title, category, description, image, price, isEquipment) VALUES ( ?, ?, ?, ?, ?, ?);";
            String queryGetLastId = "SELECT last_insert_rowid() AS id";

            try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query);
                 PreparedStatement statementGetLastId = connection.prepareStatement(queryGetLastId)) {
                // Set parameters for the prepared statement
                preparedStatement.setString(1, content.getTitle());
                preparedStatement.setString(2, content.getCategory());
                preparedStatement.setString(3, content.getDescription());
                preparedStatement.setObject(4, content.getImage());
                preparedStatement.setDouble(5, content.getPrice());
                preparedStatement.setBoolean(6, content.isEquipement());

                // Execute the insertion query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
                // Retrieve the last inserted ID
                try (ResultSet resultSet = statementGetLastId.executeQuery()) {
                    if (resultSet.next()) {
                        int lastInsertId = resultSet.getInt("id");
                        return lastInsertId;
                    } else {
                        throw new SQLException("Unable to retrieve last inserted ID");
                    }
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
        return 0;
    }

    public Content getContent(int id) throws SQLException {
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
                double price = resultSet.getDouble("price");

                // creation de l'objet
                if (isEquipment) {
                    return new Equipment(title, category, description, image, price) {
                    };
                } else {
                    return new Service(title, category, description, image, price);
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
