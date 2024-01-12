package eu.telecomnancy.directdealing.model.slot;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Reservation;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.demande.Demande;

import java.sql.SQLException;
import java.util.List;

public class SlotManager {
    public void delete(Slot slot) throws SQLException {
        // Delete all reservation related to slot
        Application app = Application.getInstance();
        List<Reservation> reservations = app.getReservationDAO().getList(slot.getId());
        reservations.forEach((reservation) -> {
            try {
                app.getReservationManager().delete(reservation);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Delete all demande related to slot
        List<Demande> demandes = app.getDemandeDAO().getBySlot(slot.getId());
        demandes.forEach((demande) -> {
            try {
                app.getDemandeManager().delete(demande);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Delete the slot
        app.getSlotDAO().delete(slot.getId());
    }
}
