package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Demande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

public class DemandeDAO {
    public int save(Demande demande) {
        // check if the demande is already in the database
        String query = "SELECT * FROM DEMANDE WHERE idDemande = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, demande.getIdDemande());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // demande already exists
                // update demande
                String queryUpdate = "UPDATE DEMANDE SET idSlot = ?, mail = ?, demande = ?, status = ? WHERE idDemande = ?";
                try (PreparedStatement preparedStatementUpdate = DatabaseAccess.connection.prepareStatement(queryUpdate)) {
                    // Set parameters for the prepared statement
                    preparedStatementUpdate.setInt(1, demande.getIdSlot());
                    preparedStatementUpdate.setString(2, demande.getMail());
                    Timestamp timestamp = new Timestamp(demande.getDemande().getTime());
                    preparedStatementUpdate.setTimestamp(3, timestamp);
                    preparedStatementUpdate.setInt(4, demande.getStatus());
                    preparedStatementUpdate.setInt(5, demande.getIdDemande());
                    // Execute the updated query
                    preparedStatementUpdate.executeUpdate();
                    return demande.getIdDemande();
                }
            } else {
                // demande doesn't exist
                // Insert new user into the DEMANDE table
                String queryInsert = "INSERT INTO DEMANDE (idSlot, mail, demande, status) VALUES (?, ?, ?, ?);";
                try (PreparedStatement preparedStatementInsert = DatabaseAccess.connection.prepareStatement(queryInsert)) {
                    // Set parameters for the prepared statement
                    preparedStatementInsert.setInt(1, demande.getIdSlot());
                    preparedStatementInsert.setString(2, demande.getMail());
                    Timestamp timestamp = new Timestamp(demande.getDemande().getTime());
                    preparedStatementInsert.setTimestamp(3, timestamp);
                    preparedStatementInsert.setInt(4, demande.getStatus());

                    // Execute the insertion query
                    preparedStatementInsert.executeUpdate();

                    // get the id of the demande
                    String queryGetLastId = "SELECT last_insert_rowid() AS id";
                    try (PreparedStatement statementGetLastId = DatabaseAccess.connection.prepareStatement(queryGetLastId)) {
                        ResultSet resultSetGetLastId = statementGetLastId.executeQuery();
                        if (resultSetGetLastId.next()) {
                            return resultSetGetLastId.getInt("idDemande");
                        }
                    }
                }
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Demande get(int idDemande) {
        // check if the demande is already in the database
        String query = "SELECT * FROM DEMANDE WHERE idDemande = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idDemande);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // demande already exists
                // update demande
                int idSlot = resultSet.getInt("idSlot");
                String mail = resultSet.getString("mail");
                Timestamp timestamp = resultSet.getTimestamp("demande");
                Date demande = new Date(timestamp.getTime());
                int status = resultSet.getInt("status");
                return new Demande(idDemande, idSlot, mail, demande, status);
            } else {
                // demande doesn't exist
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
