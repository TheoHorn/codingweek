package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OfferManager {
    public void addOffer(Offer offer) throws SQLException {
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return; // or throw an exception if needed
        }

        try (Statement statement = DatabaseAccess.connection.createStatement()) {

            // Insert new user into the ACCOUNT table
            String query = "INSERT INTO OFFER (idOwner, idContent) VALUES (?, ?);";
            try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
                // Set parameters for the prepared statement
                preparedStatement.setInt(1, offer.getOwner().getId());
                preparedStatement.setInt(2, offer.getContent().getId());

                // Execute the insertion query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
    }

    public Account getAccount(String idContent) throws SQLException {

        // getting account from mail primary key
        String query = "SELECT * FROM OFFER WHERE idContent = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(2, idContent);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // recup des infos
                int idOwner = resultSet.getInt("idOwner");

                // creation de l'objet
                Account account = AccountManager.getAccount(idOwner);

                return account;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }

    public Content getContent(int idOwner) throws SQLException {

        // getting account from mail primary key
        String query = "SELECT * FROM OFFER WHERE idContent = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idOwner);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // recup des infos
                int idContent = resultSet.getInt("idOwner");

                // creation de l'objet
                Content content = ContentManager.getContent(idContent);

                return content;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }
}
