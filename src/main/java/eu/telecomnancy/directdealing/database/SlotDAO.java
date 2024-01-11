package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Slot;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static eu.telecomnancy.directdealing.database.DatabaseAccess.connection;

/**
 * SlotDAO is the class that allows to access the SLOT table in the database
 */
public class SlotDAO {
    /**
     * get method allows to get a slot from the database
     * @param idSlot id of the slot to get
     * @return the slot if it exists, null if not
     * @throws SQLException if the connection is not open
     */
    public Slot get(int idSlot, int idOffer) throws SQLException {
        // get slot from id
        String query = "SELECT * FROM SLOT WHERE idSlot = ? AND idOffer = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSlot);
            preparedStatement.setInt(2, idOffer);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // extract infos from request result
                int recupIdSlot = resultSet.getInt("idSlot");
                Timestamp tempStartTime = resultSet.getTimestamp("startTime");
                java.util.Date startTime = new java.util.Date(tempStartTime.getTime());
                Timestamp tempEndTime = resultSet.getTimestamp("endTime");
                java.util.Date endTime = null;
                if (tempEndTime != null) {
                    endTime = new java.util.Date(tempEndTime.getTime());
                }
                int recurrence = resultSet.getInt("recurring");
                int idOfferRes = resultSet.getInt("idOffer");

                // creation of the slot and return
                return new Slot(recupIdSlot, startTime, endTime, recurrence, idOfferRes);

            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;

    }

    public List<Slot> get(int idOffer) throws SQLException {
        // get slot from idOffer
        String query = "SELECT * FROM SLOT WHERE idOffer = ?";
        ResultSet resultSet = null;

        List<Slot> slots = new ArrayList<Slot>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idOffer);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // extract infos from request result
                int recupIdSlot = resultSet.getInt("idSlot");
                Timestamp tempStartTime = resultSet.getTimestamp("startTime");
                java.util.Date startTime = new java.util.Date(tempStartTime.getTime());
                Timestamp tempEndTime = resultSet.getTimestamp("endTime");
                java.util.Date endTime = null;
                if (tempEndTime != null) {
                    endTime = new java.util.Date(tempEndTime.getTime());
                }
                int recurrence = resultSet.getInt("recurring");

                Slot addingSlot = new Slot(recupIdSlot, startTime, endTime, recurrence, idOffer);
                slots.add(addingSlot);

                // creation of the slot and return

            }
            return slots;

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

    }

    /**
     * save method allows to save a slot in the database
     * @param slot slot to save
     * @return the id of the slot saved
     * @throws SQLException if the connection is not open
     */
    public int save(Slot slot) throws SQLException {
        // check if slot already exists
        String query = "SELECT * FROM SLOT WHERE idSlot = ?";
        ResultSet resultSet = null;
        boolean find = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, slot.getId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // updaye slot
                String queryUpdate = "UPDATE SLOT SET startTime = ?, endTime = ?, recurring = ?, idOffer = ? WHERE idSlot = ?";
                try (PreparedStatement preparedStatementUpdate = connection.prepareStatement(queryUpdate)) {
                    preparedStatementUpdate.setTimestamp(1, new Timestamp(slot.getStartTime().getTime()));
                    if (slot.getEndTime() != null) {
                        preparedStatementUpdate.setTimestamp(2, new Timestamp(slot.getEndTime().getTime()));
                    } else {
                        preparedStatementUpdate.setTimestamp(2, null);
                    }
                    preparedStatementUpdate.setInt(3, slot.getRecurrence());
                    preparedStatementUpdate.setInt(4, slot.getIdOffer());
                    preparedStatementUpdate.setInt(5, slot.getId());
                    preparedStatementUpdate.executeUpdate();
                }
                return slot.getId();
            } else {
                // slot doesn't exist
                String queryInsert = "INSERT INTO SLOT (startTime, endTime, recurring, idOffer) VALUES (?, ?, ?, ?)";
                String queryGetLastId = "SELECT last_insert_rowid() AS id";
                try (PreparedStatement preparedStatementInsert = connection.prepareStatement(queryInsert);
                     PreparedStatement statementGetLastId = connection.prepareStatement(queryGetLastId)) {
                    // Set parameters for the prepared statement
                    preparedStatementInsert.setTimestamp(1, new Timestamp(slot.getStartTime().getTime()));
                    if (slot.getEndTime() != null) {
                        preparedStatementInsert.setTimestamp(2, new Timestamp(slot.getEndTime().getTime()));
                    } else {
                        preparedStatementInsert.setTimestamp(2, null);
                    }
                    preparedStatementInsert.setInt(3, slot.getRecurrence());
                    preparedStatementInsert.setInt(4, slot.getIdOffer());

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

    public Slot get(int idSlot, boolean preciseSlot) throws SQLException {
        // get slot from idOffer
        String query = "SELECT * FROM SLOT WHERE idSlot = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSlot);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // extract infos from request result
                Timestamp tempStartTime = resultSet.getTimestamp("startTime");
                java.util.Date startTime = new java.util.Date(tempStartTime.getTime());
                Timestamp tempEndTime = resultSet.getTimestamp("endTime");
                Date endTime;
                if (tempEndTime == null) {
                    endTime = null;
                } else {
                    endTime = new java.util.Date(tempEndTime.getTime());
                }
                int recurrence = resultSet.getInt("recurring");
                int idOffer = resultSet.getInt("idOffer");

                return new Slot(idSlot, startTime, endTime, recurrence, idOffer);

                // creation of the slot and return

            }
            return null;

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

    }


}
