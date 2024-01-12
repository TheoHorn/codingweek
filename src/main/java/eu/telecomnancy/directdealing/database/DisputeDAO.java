package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.dispute.Dispute;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisputeDAO {
    public int save(Dispute dispute) {
        // check if the dispute is already in the database
        String query = "SELECT * FROM DISPUTE WHERE idDispute = ?";
        ResultSet resultSet;
        try(PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)){
            preparedStatement.setInt(1, dispute.getIdDispute());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                // dispute already exists
                // update dispute
                String queryUpdate = "UPDATE DISPUTE SET content = ?, attacker = ?, defender = ? WHERE idDispute = ?";
                try(PreparedStatement preparedStatementUpdate = DatabaseAccess.connection.prepareStatement(queryUpdate)){
                    // Set parameters for the prepared statement
                    preparedStatementUpdate.setString(1, dispute.getContent());
                    preparedStatementUpdate.setString(2, dispute.getAttacker());
                    preparedStatementUpdate.setString(3, dispute.getDefender());
                    preparedStatementUpdate.setInt(4, dispute.getIdDispute());
                    // Execute the updated query
                    preparedStatementUpdate.executeUpdate();
                    return dispute.getIdDispute();
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                // dispute doesn't exist
                // Insert new dispute into the DISPUTE table
                String queryInsert = "INSERT INTO DISPUTE (idDemande, content, attacker, defender) VALUES (?, ?, ?, ?);";
                try(PreparedStatement preparedStatementInsert = DatabaseAccess.connection.prepareStatement(queryInsert)){
                    // Set parameters for the prepared statement
                    preparedStatementInsert.setInt(1, dispute.getIdDemande());
                    preparedStatementInsert.setString(2, dispute.getContent());
                    preparedStatementInsert.setString(3, dispute.getAttacker());
                    preparedStatementInsert.setString(4, dispute.getDefender());
                    // Execute the insertion query
                    preparedStatementInsert.executeUpdate();
                    // get the id of the dispute
                    String queryGetLastId = "SELECT last_insert_rowid() AS id";
                    try(PreparedStatement preparedStatementGetLastId = DatabaseAccess.connection.prepareStatement(queryGetLastId)){
                        resultSet = preparedStatementGetLastId.executeQuery();
                        if(resultSet.next()){
                            return resultSet.getInt("id");
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public List<Dispute> get(){
        // get all disputes
        String query = "SELECT * FROM DISPUTE";
        ResultSet resultSet = null;
        List<Dispute> disputes = new ArrayList<Dispute>();
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)){
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { // Check if there are results
                // message already exists
                // get message
                int idDispute = resultSet.getInt("idDispute");
                int idDemande = resultSet.getInt("idDemande");
                String content = resultSet.getString("content");
                String attacker = resultSet.getString("attacker");
                String defender = resultSet.getString("defender");
                disputes.add(new Dispute(idDispute, idDemande, content, attacker, defender));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disputes;
    }

    public void delete(int idDispute) throws SQLException {

        String query = "DELETE FROM DISPUTE WHERE idDispute = ?;";

        PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query);
        preparedStatement.setInt(1, idDispute);
        preparedStatement.execute();
    }

}
