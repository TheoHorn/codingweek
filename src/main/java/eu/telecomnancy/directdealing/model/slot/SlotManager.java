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
                app.getSlotManager().delete(slot);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Delete all demande related to slot
        Demande demandes = app.getDemandeDAO().get(slot.getId());
        if (demandes != null)
            app.getDemandeManager().delete(demandes);

        // Delete the slot
        app.getSlotDAO().delete(slot.getId());
    }
}
