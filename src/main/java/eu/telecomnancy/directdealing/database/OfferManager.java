package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OfferManager {
    public static void addOffer(Offer offer) throws SQLException {
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return; // or throw an exception if needed
        }

        try (Statement statement = DatabaseAccess.connection.createStatement()) {

            // Insert new user into the ACCOUNT table
            String query = "INSERT INTO OFFER (idOffer, mail, isRequest, idContent, idCreneau) VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
                // Set parameters for the prepared statement
                preparedStatement.setInt(1, offer.getId());
                preparedStatement.setString(2, offer.getOwner().getEmail());
                preparedStatement.setBoolean(3, offer.isRequest());
                preparedStatement.setInt(4, offer.getContent().getId());
                preparedStatement.setInt(5, offer.getSlot().getId());

                // Execute the insertion query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
    }

    public static Account getAccount(String idContent) throws SQLException {

        // getting account from mail primary key
        String query = "SELECT * FROM OFFER WHERE idContent = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, idContent);
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

    public static Content getContent(int idOwner) throws SQLException {

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

    public static Offer getOffer(int idOffer) throws SQLException{
        // getting account from mail primary key
        String query = "SELECT * FROM OFFER WHERE idOffer = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idOffer);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // recup des infos
                String mail = resultSet.getString("mail");
                boolean request = resultSet.getBoolean("isRequest");
                int idContent = resultSet.getInt("idContent");
                int idCreneau = resultSet.getInt("idCreneau");

                // creation de l'objet
                return new Offer(AccountManager.getAccount(mail), request, ContentManager.getContent(idContent), CreneauManager.getCreneau(idCreneau));

            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }
}
