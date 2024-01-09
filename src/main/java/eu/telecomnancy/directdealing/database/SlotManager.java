package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Slot;

import java.sql.*;
import java.util.Date;

import static eu.telecomnancy.directdealing.database.DatabaseAccess.connection;


public class SlotManager {
    public static Slot getSlot(int idSlot) throws SQLException {
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

    public static int addSlot(Date startDate, Date endDate, int recurrence) throws SQLException {
        // adding a new slot in the database

        String query = "INSERT INTO SLOT (startTime, endTime, recurrence) VALUES (?, ?, ?)";
        String queryGetLastId = "SELECT last_insert_rowid() AS id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             PreparedStatement statementGetLastId = connection.prepareStatement(queryGetLastId)) {

            preparedStatement.setTimestamp(1, new Timestamp(startDate.getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(endDate.getTime()));
            preparedStatement.setInt(3, recurrence);

            // Execute the insertion
            preparedStatement.executeUpdate();

            // Retrieve the last inserted ID
            try (ResultSet resultSet = statementGetLastId.executeQuery()) {
                if (resultSet.next()) {
                    int lastInsertId = resultSet.getInt("id");
                    return lastInsertId;
                } else {
                    throw new SQLException("Unable to retrieve last inserted ID");
                }
            }
        }
    }


}
