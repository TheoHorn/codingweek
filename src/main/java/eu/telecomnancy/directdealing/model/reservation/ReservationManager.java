package eu.telecomnancy.directdealing.model.reservation;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Reservation;

import java.sql.SQLException;

public class ReservationManager {

    public void delete(Reservation reservation) throws SQLException {
        Application.getInstance().getReservationDAO().delete(reservation.getIdSlot(), reservation.getEmailReserver());
    }
}
