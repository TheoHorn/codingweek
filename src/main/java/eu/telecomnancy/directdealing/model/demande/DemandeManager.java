package eu.telecomnancy.directdealing.model.demande;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.offer.Offer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;

public class DemandeManager {
    public List<Demande> getAllMyAnswer() throws SQLException {
        String myMail = app.getCurrentUser().getEmail();
        List<Demande> myAnswers = new ArrayList<Demande>();
        List<Offer> myOffers = app.getOfferDAO().get(myMail);
        for (Demande demande : app.getDemandeDAO().get()){
            int slotDemande = demande.getIdSlot();
            int offerDemande = app.getSlotDAO().get(slotDemande,false).getIdOffer();
            if (myOffers.contains(app.getOfferDAO().get(offerDemande))){
                myAnswers.add(demande);
            }
        }
        return myAnswers;
    }
}
