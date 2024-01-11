package eu.telecomnancy.directdealing.model.reservation;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Reservation;

import java.sql.SQLException;

public class ReservationManager {
    private Application app;

    public ReservationManager(){
        this.app = Application.getInstance();
    }

    public void delete(Reservation reservation) throws SQLException {
        app.getReservationDAO().delete(reservation.getIdSlot());
    }
}
