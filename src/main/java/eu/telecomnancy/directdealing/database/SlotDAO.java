package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Slot;

import java.sql.*;
import java.util.Date;

import static eu.telecomnancy.directdealing.Main.app;
import static eu.telecomnancy.directdealing.database.DatabaseAccess.connection;


public class SlotDAO {
    public Slot getSlot(int idSlot) throws SQLException {
        // get slot from id
        String query = "SELECT * FROM SLOT WHERE idSlot = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSlot);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // extract infos from request result
                int recupIdSlot = resultSet.getInt("idSlot");
                Timestamp tempStartTime = resultSet.getTimestamp("startTime");
                java.util.Date startTime = new java.util.Date(tempStartTime.getTime());
                Timestamp tempEndTime = resultSet.getTimestamp("endTime");
                java.util.Date endTime = new java.util.Date(tempEndTime.getTime());
                int recurrence = resultSet.getInt("recurrence");

                // creation of the slot and return
                return new Slot(recupIdSlot, startTime, endTime, recurrence);

            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;

    }

    public int save(Slot slot) throws SQLException {
        // check if slot already exists
        String query = "SELECT * FROM SLOT WHERE startTime = ? AND endTime = ? AND recurrence = ?";
        ResultSet resultSet = null;
        boolean find = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, new Timestamp(slot.getStartTime().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(slot.getEndTime().getTime()));
            preparedStatement.setInt(3, slot.getRecurrence());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                find = true;
                // extract infos from request result
                int idSlot = resultSet.getInt("idSlot");
                return idSlot;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

        // insert slot in database
        if (!find) {
            String queryInsert = "INSERT INTO SLOT (startTime, endTime, recurrence) VALUES (?, ?, ?)";
            String queryGetLastId = "SELECT last_insert_rowid() AS id";

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryInsert);
                 PreparedStatement statementGetLastId = connection.prepareStatement(queryGetLastId)) {


                preparedStatement.setTimestamp(1, new Timestamp(slot.getStartTime().getTime()));
                preparedStatement.setTimestamp(2, new Timestamp(slot.getEndTime().getTime()));
                preparedStatement.setInt(3, slot.getRecurrence());

                // Execute the insertion
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
    }
        return -1;
    }


}
