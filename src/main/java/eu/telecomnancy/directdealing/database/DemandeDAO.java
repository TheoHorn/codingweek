package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.demande.Demande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                String queryUpdate = "UPDATE DEMANDE SET idSlot = ?, mail = ?, dateDemande = ?, status = ? WHERE idDemande = ?";
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
                String queryInsert = "INSERT INTO DEMANDE (idSlot, mail, dateDemande, status) VALUES (?, ?, ?, ?);";
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
                            return resultSetGetLastId.getInt("id");
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

    public Demande get(String column, int id) {
        // check if the demande is already in the database
        String query = "SELECT * FROM DEMANDE WHERE " + column + " = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // demande already exists
                // update demande
                int idSlot = resultSet.getInt("idSlot");
                String mail = resultSet.getString("mail");
                Timestamp timestamp = resultSet.getTimestamp("dateDemande");
                Date demande = new Date(timestamp.getTime());
                int status = resultSet.getInt("status");
                return new Demande(id, idSlot, mail, demande, status);
            } else {
                // demande doesn't exist
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Demande> get(String mail) {
        // check if the demande is already in the database
        String query = "SELECT * FROM DEMANDE WHERE mail = ?";
        ResultSet resultSet = null;
        List<Demande> demandes = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // demande already exists
                // update demande
                int idSlot = resultSet.getInt("idSlot");
                int idDemande = resultSet.getInt("idDemande");
                Timestamp timestamp = resultSet.getTimestamp("dateDemande");
                Date demande = new Date(timestamp.getTime());
                int status = resultSet.getInt("status");

                demandes.add(new Demande(idDemande, idSlot, mail, demande, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demandes;
    }

    public List<Demande> getBySlot(int idSlot) {
        // check if the demande is already in the database
        String query = "SELECT * FROM DEMANDE WHERE mail = ?";
        ResultSet resultSet = null;
        List<Demande> demandes = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSlot);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // demande already exists
                // update demande
                String mail = resultSet.getString("mail");
                int idDemande = resultSet.getInt("idDemande");
                Timestamp timestamp = resultSet.getTimestamp("dateDemande");
                Date demande = new Date(timestamp.getTime());
                int status = resultSet.getInt("status");

                demandes.add(new Demande(idDemande, idSlot, mail, demande, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demandes;
    }

    public List<Demande> get() throws SQLException {
        // get all demands
        List<Demande> demands = new ArrayList<Demande>();

        String query = "SELECT * FROM DEMANDE";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // recup des infos
                int idDemande = resultSet.getInt("idDemande");
                int idSlot = resultSet.getInt("idSlot");
                String mail = resultSet.getString("mail");
                Timestamp timestamp = resultSet.getTimestamp("dateDemande");
                Date demande = new Date(timestamp.getTime());
                int status = resultSet.getInt("status");
                demands.add(new Demande(idDemande, idSlot, mail, demande, status));
            }
            return demands;

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public void delete(int idDemande) throws SQLException {

        String query = "DELETE FROM DEMANDE WHERE idDemande = ?;";

        PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query);
        preparedStatement.setInt(1, idDemande);
        preparedStatement.execute();
    }

}
