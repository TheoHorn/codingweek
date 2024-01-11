package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.model.offer.Request;
import javafx.scene.SubScene;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;
import static eu.telecomnancy.directdealing.database.DatabaseAccess.connection;

/**
 * OfferDAO is the class that allows to access the OFFER table in the database
 */
public class OfferDAO {
    /**
     * save method allows to save an offer in the database
     * @param offer offer to save
     * @return the id of the offer saved
     * @throws SQLException if the connection is not open
     */
    public int save(Offer offer) throws SQLException {
        // check if the offer exist
        String query = "SELECT * FROM OFFER WHERE idOffer = ?";
        ResultSet resultSet = null;
        boolean find = false;
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offer.getIdOffer());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // update offer
                String queryUpdate = "UPDATE OFFER SET mail = ?, isRequest = ?, idContent = ? WHERE idOffer = ?";
                try (PreparedStatement preparedStatementUpdate = DatabaseAccess.connection.prepareStatement(queryUpdate)) {
                    // Set parameters for the prepared statement
                    preparedStatementUpdate.setString(1, offer.getMail());
                    preparedStatementUpdate.setBoolean(2, offer.isRequest());
                    preparedStatementUpdate.setInt(3, offer.getIdContent());
                    preparedStatementUpdate.setInt(4, offer.getIdOffer());
                    // Execute the updated query
                    preparedStatementUpdate.executeUpdate();
                    return offer.getIdOffer();
                }
            } else {
                // offer doesn't exist
                // Insert new user into the ACCOUNT table
                String queryInsert = "INSERT INTO OFFER (mail, isRequest, idContent) VALUES (?, ?, ?);";
                String queryGetLastId = "SELECT last_insert_rowid() AS id";

                try (PreparedStatement preparedStatementInsert = connection.prepareStatement(queryInsert);
                     PreparedStatement statementGetLastId = connection.prepareStatement(queryGetLastId)) {
                    // Set parameters for the prepared statement
                    preparedStatementInsert.setString(1, offer.getMail());
                    if (offer.isRequest()){
                        preparedStatementInsert.setBoolean(2, true);
                    } else {
                        preparedStatementInsert.setBoolean(2, false);
                    }
                    preparedStatementInsert.setInt(3, offer.getIdContent());
                    // Execute the insertion query
                    preparedStatementInsert.executeUpdate();
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
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    /**
     * get method allows to get an offer from the database
     * @param idOffer id of the offer to get
     * @return the offer if it exists, null if not
     * @throws SQLException if the connection is not open
     */
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

                if (request){
                    System.out.println(app.getContentDAO().get(idContent).getTitle());
                    Request res =  new Request(mail, true, idContent);
                    res.setIdOffer(idOffer);
                    return res;
                } else {
                    Proposal res =  new Proposal(mail, false, idContent);
                    res.setIdOffer(idOffer);
                    return res;
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
        String query = "SELECT idOffer FROM OFFER";
        ResultSet resultSet = null;

        List<Offer> offers = new ArrayList<Offer>();


        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // recup des infos
                int idOffer = resultSet.getInt("idOffer");
                offers.add(get(idOffer));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return offers;
    }

    public List<Offer> get(String mail) throws SQLException {
        // getting all offers from user
        String query = "SELECT idOffer FROM OFFER WHERE mail = ?";
        ResultSet resultSet = null;
        List<Offer> offers = new ArrayList<Offer>();
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // recup des infos
                int idOffer = resultSet.getInt("idOffer");
                offers.add(get(idOffer));
            }
            return offers;

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public void delete(int idOffer) throws SQLException {

        String query = "DELETE FROM OFFER WHERE idOffer = ?;";

        PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query);
        preparedStatement.setInt(1, idOffer);
        preparedStatement.execute();
    }
}
