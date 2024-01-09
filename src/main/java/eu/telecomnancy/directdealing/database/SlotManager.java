package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Admin;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

public class SlotManager {
    public static Slot getSlotWithId(int idSlot) throws SQLException {
        String query = "SELECT * FROM SLOT WHERE idSlot = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSlot);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // recup des infos
                int recupIdSlot = resultSet.getInt("idSlot");
                Timestamp tempStartTime = resultSet.getTimestamp("startTime");
                java.util.Date startTime = new java.util.Date(tempStartTime.getTime());
                Timestamp tempEndTime = resultSet.getTimestamp("endTime");
                java.util.Date endTime = new java.util.Date(tempEndTime.getTime());
                int recurrence = resultSet.getInt("recurrence");

                // Création du slot et renvoie
                return new Slot(recupIdSlot, startTime, endTime, recurrence);

            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;

    }

}
