package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static eu.telecomnancy.directdealing.database.DatabaseAccess.connection;

/**
 * ReservationDAO is the class that allows to access the RESERVATION table in the database
 */
public class ReservationDAO {
    /**
     * save method allows to save a reservation in the database
     * @param reservation reservation to save
     * @return true if the reservation has been saved, false if not
     * @throws SQLException if the connection is not open
     */
    public boolean save(Reservation reservation) throws SQLException {
        // check if reservation already exists
        String query = "SELECT * FROM RESERVATION WHERE mail = ? AND idSlot = ?";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, reservation.getEmailReserver());
            preparedStatement.setInt(2, reservation.getIdSlot());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results// reservation already exists
                return false;
            } else {
                String queryAdding = "INSERT INTO RESERVATION (mail, idSlot, dateReservation) VALUES (?, ?, ?);";
                try (PreparedStatement preparedStatementInsert = DatabaseAccess.connection.prepareStatement(queryAdding)) {
                    // Set parameters for the prepared statement
                    preparedStatementInsert.setString(1, reservation.getEmailReserver());
                    preparedStatementInsert.setInt(2, reservation.getIdSlot());
                    Timestamp timestamp = new Timestamp(reservation.getReservationDate().getTime());
                    preparedStatementInsert.setTimestamp(3, timestamp);

                    // Execute the insertion query
                    preparedStatementInsert.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return false;
    }

    /**
     * get method allows to get a reservation from the database
     * @param idSlot id of the slot of the reservation
     * @return the reservation if it exists, null if not
     * @throws SQLException if the connection is not open
     */
    public Reservation get(int idSlot) throws SQLException {
        // get reservation with primary_key idSlot

        String query = "SELECT * FROM RESERVATION WHERE idSlot = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSlot);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Check if there are results
                // extract infos from request result
                String mailRes = resultSet.getString("mail");
                int idSlotRes = resultSet.getInt("idSlot");
                Timestamp timestamp = resultSet.getTimestamp("dateReservation");
                Date date = new Date(timestamp.getTime());

                // creation of the reservation and return
                return new Reservation(mailRes, idSlotRes, date);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }


    public List<Reservation> get(String mail) throws SQLException {
        // get reservation with primary_key (mail, idSlot)

        String query = "SELECT * FROM RESERVATION WHERE mail = ?";
        ResultSet resultSet = null;
        List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // extract infos from request result
                String mailRes = resultSet.getString("mail");
                int idSlotRes = resultSet.getInt("idSlot");
                Timestamp timestamp = resultSet.getTimestamp("dateReservation");
                Date date = new Date(timestamp.getTime());

                // creation of the reservation and return
                reservations.add(new Reservation(mailRes, idSlotRes, date));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return reservations;
    }


    public List<Reservation> getList(int idSlot) throws SQLException {
        // get reservation with primary_key (mail, idSlot)

        String query = "SELECT * FROM RESERVATION WHERE idSlot = ?";
        ResultSet resultSet = null;
        List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSlot);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Check if there are results
                // extract infos from request result
                String mailRes = resultSet.getString("mail");
                int idSlotRes = resultSet.getInt("idSlot");
                Timestamp timestamp = resultSet.getTimestamp("dateReservation");
                Date date = new Date(timestamp.getTime());

                // creation of the reservation and return
                reservations.add(new Reservation(mailRes, idSlotRes, date));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return reservations;
    }

    public void delete(int idSlot) throws SQLException {

        String query = "DELETE FROM RESERVATION WHERE idSlot = ?;";

        PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query);
        preparedStatement.setInt(1, idSlot);
        preparedStatement.execute();
    }
}

