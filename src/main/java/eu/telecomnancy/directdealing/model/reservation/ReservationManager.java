package eu.telecomnancy.directdealing.model.reservation;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Reservation;
import eu.telecomnancy.directdealing.model.demande.Demande;

import java.sql.SQLException;

public class ReservationManager {
    public Reservation getFromDemand(Demande demande) {
        return new Reservation(demande.getMail(), demande.getIdSlot(), demande.getDemande());
    }

    public void delete(Reservation reservation) throws SQLException {
        Application.getInstance().getReservationDAO().delete(reservation.getIdSlot());
    }
}
