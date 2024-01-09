package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.Content;
import eu.telecomnancy.directdealing.model.Equipment;
import eu.telecomnancy.directdealing.model.Services;
import javafx.scene.image.Image;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ReservationManager {
    public static void addReservation(Reservation reservation) throws SQLException {
        // Check database connection
        if (DatabaseAccess.connection == null || DatabaseAccess.connection.isClosed()) {
            System.err.println("Database connection is not open.");
            return; // or throw an exception if needed
        }

        try (Statement statement = DatabaseAccess.connection.createStatement()) {

            // Insert new user into the ACCOUNT table
            String query = "INSERT INTO CONTENT (offerId, mail, slotId, date) VALUES (?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
                // Set parameters for the prepared statement
                preparedStatement.setInt(1, reservation.getOffer().getId());
                preparedStatement.setString(2, reservation.getMail());
                preparedStatement.setInt(3, reservation.getSlot().getId());
                preparedStatement.setDate(4, reservation.getDate());

                // Execute the insertion query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
    }

    public static Reservation getReservation(int offerId, int slotId) throws SQLException {
        // getting account from mail primary key
        String query = "SELECT * FROM ACCOUNT WHERE offerId = ? AND slotId = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offerId);
            preparedStatement.setInt(2, slotId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // recup des infos
                int offerId1 = resultSet.getInt("offerId");
                String mail = resultSet.getString("mail");
                int slotId1 = resultSet.getInt("slotId");
                Date date = resultSet.getDate("date");

                // creation de l'objet
                return new Reservation(offerId1, mail, slotId1, date);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }
}
