package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.messaging.Messaging;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessagingDAO {

    /**
     * Save a message into the database
     * @param messaging the message to save
     * @return the id of the message
     */
    public int save(Messaging messaging) {
        // check if the message is already in the database
        String query = "SELECT * FROM MESSAGING WHERE idMessage = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, messaging.getIdMsg());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // message already exists
                // update message
                String queryUpdate = "UPDATE MESSAGING SET content = ?, sender = ?, receiver = ?, timestamp = ? WHERE idMessage = ?";
                try (PreparedStatement preparedStatementUpdate = DatabaseAccess.connection.prepareStatement(queryUpdate)) {
                    // Set parameters for the prepared statement
                    preparedStatementUpdate.setString(1, messaging.getContent());
                    preparedStatementUpdate.setString(2, messaging.getSender());
                    preparedStatementUpdate.setString(3, messaging.getReceiver());
                    Timestamp timestamp = new Timestamp(messaging.getDate().getTime());
                    preparedStatementUpdate.setTimestamp(4, timestamp);
                    preparedStatementUpdate.setInt(5, messaging.getIdMsg());
                    // Execute the updated query
                    preparedStatementUpdate.executeUpdate();
                    return messaging.getIdMsg();
                }
            } else {
                // message doesn't exist
                // Insert new user into the MESSAGING table
                String queryInsert = "INSERT INTO MESSAGING (content, sender, receiver, timestamp) VALUES (?, ?, ?, ?);";
                try (PreparedStatement preparedStatementInsert = DatabaseAccess.connection.prepareStatement(queryInsert)) {
                    // Set parameters for the prepared statement
                    preparedStatementInsert.setString(1, messaging.getContent());
                    preparedStatementInsert.setString(2, messaging.getSender());
                    preparedStatementInsert.setString(3, messaging.getReceiver());
                    Timestamp timestamp = new Timestamp(messaging.getDate().getTime());
                    preparedStatementInsert.setTimestamp(4, timestamp);

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


    /**
     * get method allows to get a message from the database
     * @param idMessage id of the message to get
     * @return the message
     */
    public Messaging get(int idMessage) {
        // check if the message is already in the database
        String query = "SELECT * FROM MESSAGING WHERE idMessage = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idMessage);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // message already exists
                // get message
                String content = resultSet.getString("content");
                String sender = resultSet.getString("sender");
                String receiver = resultSet.getString("receiver");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                return new Messaging(idMessage, content, sender, receiver, timestamp);
            } else {
                // message doesn't exist
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get method allows to get all messages from the database
     * @return the list of messages
     */
    public List<Messaging> get(){
        // get all messages
        List<Messaging> messages = new ArrayList<>();

        String query = "SELECT * FROM MESSAGING";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // message already exists
                // get message
                int idMessage = resultSet.getInt("idMessage");
                String content = resultSet.getString("content");
                String sender = resultSet.getString("sender");
                String receiver = resultSet.getString("receiver");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                messages.add(new Messaging(idMessage, content, sender, receiver, timestamp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
}
