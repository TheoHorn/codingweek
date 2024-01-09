package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.*;
import javafx.scene.image.Image;

import java.sql.*;
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
                preparedStatement.setString(2, reservation.getEmailReserver());
                preparedStatement.setInt(3, reservation.getSlot().getId());
                Timestamp timestamp = new Timestamp(reservation.getReservationDate().getTime());
                preparedStatement.setTimestamp(4, timestamp);

                // Execute the insertion query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
    }

    public static Reservation getReservation(int idOffer, int idSlot) throws SQLException {
        // get reservation with primary_key (idOffer, idSlot)

        String query = "SELECT * FROM ACCOUNT WHERE idOffer = ? AND idSlot = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idOffer);
            preparedStatement.setInt(2, idSlot);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // extract infos from request result
                int idOfferRes = resultSet.getInt("idOffer");
                String mail = resultSet.getString("mail");
                int idSlotRes = resultSet.getInt("idSlot");
                Date date = resultSet.getDate("dateReservation");

                // creation of the reservation and return
                return new Reservation(OfferManager.getOffer(idOfferRes), mail, SlotManager.getSlot(idSlotRes), date);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }
}
