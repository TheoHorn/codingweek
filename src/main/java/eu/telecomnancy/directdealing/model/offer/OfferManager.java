package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Slot;

import java.sql.SQLException;
import java.util.List;

public class OfferManager {
    private final Application app;

    public OfferManager(){
        this.app = Application.getInstance();
    }

    public void delete(Offer offer) throws SQLException {
        // Delete all slots related to the offer
        List<Slot> slots = app.getSlotDAO().get(offer.getIdOffer());
        slots.forEach((slot) -> {
            try {
                app.getSlotManager().delete(slot);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Delete the offer
        app.getOfferDAO().delete(offer.getIdOffer());
    }
}
