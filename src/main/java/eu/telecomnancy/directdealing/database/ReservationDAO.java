package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.*;

import java.sql.*;
import java.util.Date;

import static eu.telecomnancy.directdealing.Main.app;
import static eu.telecomnancy.directdealing.database.DatabaseAccess.connection;

public class ReservationDAO {
    public boolean save(Reservation reservation) throws SQLException {
        // check if reservation already exists
        String query = "SELECT * FROM SLOT WHERE idOffer = ? AND mail = ? AND idSlot = ?";
        ResultSet resultSet = null;
        boolean find = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, reservation.getOffer().getId());
            preparedStatement.setString(2, reservation.getEmailReserver());
            preparedStatement.setInt(3, reservation.getSlot().getId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                find = true;
                // reservation already exists
                return false;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        if (!find){
            // reservation doesn't exist
            try (Statement statement = DatabaseAccess.connection.createStatement()) {
                String queryAdding = "INSERT INTO CONTENT (idOffer, mail, idSlot, dateReservation) VALUES (?, ?, ?, ?);";
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
                    return true;
                }
            } catch (SQLException e) {
                // Handle SQL exceptions
                e.printStackTrace();
                }
            }
        }
        return false;
    }

    public Reservation getReservation(int idOffer, int idSlot) throws SQLException {
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
                return new Reservation(app.getOfferManager().getOffer(idOfferRes), mail, app.getSlotDAO().getSlot(idSlotRes), date);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }
}
