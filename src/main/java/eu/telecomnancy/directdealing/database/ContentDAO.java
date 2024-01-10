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

/**
 * ContentDAO is the class responsible for the management of the CONTENT table in the database.
 */
public class ContentDAO {

    /**
     * Save a content in the database
     * @param content the content to save
     * @return the id of the content saved
     * @throws SQLException if an error occurs
     */
    public int save(Content content) throws SQLException {
        // Check if content already exists
        String query = "SELECT * FROM CONTENT WHERE idContent = ?";
        ResultSet resultSet = null;
        boolean find = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, content.getIdContent());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // update content
                String queryUpdate = "UPDATE CONTENT SET title = ?, category = ?, description = ?, image = ?, price = ?, isEquipment = ? WHERE idContent = ?";
                try (PreparedStatement preparedStatementUpdate = connection.prepareStatement(queryUpdate)) {
                    // Set parameters for the prepared statement
                    preparedStatementUpdate.setString(1, content.getTitle());
                    preparedStatementUpdate.setString(2, content.getCategory());
                    preparedStatementUpdate.setString(3, content.getDescription());
                    preparedStatementUpdate.setObject(4, content.getImage());
                    preparedStatementUpdate.setDouble(5, content.getPrice());
                    preparedStatementUpdate.setBoolean(6, content.isEquipment());
                    preparedStatementUpdate.setInt(7, content.getIdContent());
                    // Execute the updated query
                    preparedStatementUpdate.executeUpdate();
                    return content.getIdContent();
                }
            } else {
                try (Statement statement = DatabaseAccess.connection.createStatement()) {
                    // Insert new user into the ACCOUNT table
                    String queryInsert = "INSERT INTO CONTENT (title, category, description, image, price, isEquipment) VALUES ( ?, ?, ?, ?, ?, ?);";
                    String queryGetLastId = "SELECT last_insert_rowid() AS id";

                    try (PreparedStatement preparedStatementInsert = DatabaseAccess.connection.prepareStatement(queryInsert);
                         PreparedStatement statementGetLastId = connection.prepareStatement(queryGetLastId)) {
                        // Set parameters for the prepared statement
                        preparedStatementInsert.setString(1, content.getTitle());
                        preparedStatementInsert.setString(2, content.getCategory());
                        preparedStatementInsert.setString(3, content.getDescription());
                        preparedStatementInsert.setObject(4, content.getImage());
                        preparedStatementInsert.setDouble(5, content.getPrice());
                        preparedStatementInsert.setBoolean(6, content.isEquipment());
                        // Execute the insertion query
                        preparedStatementInsert.executeUpdate();
                        // Retrieve the last inserted ID
                        try (ResultSet resultSetInsert = statementGetLastId.executeQuery()) {
                            if (resultSetInsert.next()) {
                                int lastInsertId = resultSetInsert.getInt("id");
                                return lastInsertId;
                            } else {
                                throw new SQLException("Unable to retrieve last inserted ID");
                            }
                        } catch (SQLException e) {
                            // Handle SQL exceptions
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    // Handle SQL exceptions
                    e.printStackTrace();
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return -1;
    }

    /**
     * Get a content from the database
     * @param idContent the id of the content to get
     * @return the content
     * @throws SQLException if an error occurs
     */
    public Content get(int idContent) throws SQLException {
        // getting account from mail primary key
        String query = "SELECT * FROM CONTENT WHERE idContent = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idContent);
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
