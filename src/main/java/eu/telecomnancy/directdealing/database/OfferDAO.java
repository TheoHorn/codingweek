package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.model.offer.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;
import static eu.telecomnancy.directdealing.database.DatabaseAccess.connection;

public class OfferDAO {
    public int save(Offer offer) throws SQLException {
        // check if the offer exist
        String query = "SELECT * FROM OFFER WHERE idOffer = ?";
        ResultSet resultSet = null;
        boolean find = false;
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offer.getIdOffer());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                find = true;
                // update offer
                String queryUpdate = "UPDATE OFFER SET mail = ?, isRequest = ?, idContent = ?, idSlot = ? WHERE idOffer = ?";
                try (PreparedStatement preparedStatementUpdate = DatabaseAccess.connection.prepareStatement(queryUpdate)) {
                    // Set parameters for the prepared statement
                    preparedStatementUpdate.setString(1, offer.getOwner().getEmail());
                    if (offer.isRequest()){
                        preparedStatementUpdate.setBoolean(2, true);
                    } else {
                        preparedStatementUpdate.setBoolean(2, false);
                    }
                    preparedStatementUpdate.setInt(3, offer.getContent().getId());
                    preparedStatementUpdate.setInt(4, offer.getSlot().getId());
                    preparedStatementUpdate.setInt(5, offer.getIdOffer());
                    // Execute the updated query
                    preparedStatementUpdate.executeUpdate();
                    return offer.getIdOffer();
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        if (!find) {
            // offer doesn't exist
            // Insert new user into the ACCOUNT table
            String queryInsert = "INSERT INTO OFFER (mail, isRequest, idContent, idSlot) VALUES (?, ?, ?, ?);";
            String queryGetLastId = "SELECT last_insert_rowid() AS id";

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryInsert);
                 PreparedStatement statementGetLastId = connection.prepareStatement(queryGetLastId)) {
                // Set parameters for the prepared statement
                preparedStatement.setString(1, offer.getOwner().getEmail());
                if (offer.isRequest()){
                    preparedStatement.setBoolean(2, true);
                } else {
                    preparedStatement.setBoolean(2, false);
                }
                preparedStatement.setInt(3, offer.getContent().getId());
                preparedStatement.setInt(4, offer.getSlot().getId());
                // Execute the insertion query
                preparedStatement.executeUpdate();
                // Retrieve the last inserted ID
                try (ResultSet resultSetID = statementGetLastId.executeQuery()) {
                    if (resultSetID.next()) {
                        int lastInsertId = resultSetID.getInt("id");
                        return lastInsertId;
                    } else {
                        throw new SQLException("Unable to retrieve last inserted ID");
                    }
                }
            }
        }
        return -1;
    }

    public Offer get(int idOffer) throws SQLException{
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
                    Slot slot =  app.getSlotDAO().get(idSlot);
                    return new Request((User) app.getAccountDAO().get(mail), app.getContentDAO().get(idContent), slot, true);
                } else {
                    Slot slot =  app.getSlotDAO().get(idSlot);
                    return new Proposal((User) app.getAccountDAO().get(mail), app.getContentDAO().get(idContent), slot,false);
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }

    public List<Offer> get() throws SQLException{
        // getting offers from idContent primary key
        String query = "SELECT idContent FROM OFFER";
        ResultSet resultSet = null;

        List<Offer> offers = new ArrayList<Offer>();


        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // recup des infos
                int idContent = resultSet.getInt("idContent");
                offers.add(get(idContent));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return offers;
    }

    public List<Offer> get(User user) throws SQLException {
        // getting all offers from user
        String query = "SELECT idContent FROM OFFER WHERE mail = ?";
        ResultSet resultSet = null;
        List<Offer> offers = new ArrayList<Offer>();
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getEmail());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // recup des infos
                int idContent = resultSet.getInt("idContent");
                offers.add(get(idContent));
                return offers;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
    }


}
