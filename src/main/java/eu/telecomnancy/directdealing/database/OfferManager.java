package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.model.offer.Request;

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
            String query = "INSERT INTO OFFER (idOffer, mail, isRequest, idContent, idSlot) VALUES (?, ?, ?, ?, ?);";
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

    public static void addRequest(Request request) throws SQLException {
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return; // or throw an exception if needed
        }

        try (Statement statement = DatabaseAccess.connection.createStatement()) {

            // Insert new user into the ACCOUNT table
            String query = "INSERT INTO OFFER (mail, isRequest, idContent, idSlot) VALUES (?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
                // Set parameters for the prepared statement
                preparedStatement.setString(1, request.getOwner().getEmail());
                preparedStatement.setBoolean(2, true);
                preparedStatement.setInt(3, request.getContent().getId());
                preparedStatement.setInt(4, request.getSlot().getId());

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
                int idSlot = resultSet.getInt("idSlot");

                if (request){
                    Slot slot =  SlotManager.getSlot(idSlot);
                    return new Request((User) AccountManager.getAccount(mail), ContentManager.getContent(idContent), slot, true);
                } else {
                    return new Proposal((User) AccountManager.getAccount(mail), ContentManager.getContent(idContent), 0);
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
