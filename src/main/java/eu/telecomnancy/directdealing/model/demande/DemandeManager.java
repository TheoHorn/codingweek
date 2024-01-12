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
            for (Offer offer : myOffers){
                if (offer.getIdOffer() == offerDemande){
                    myAnswers.add(demande);
                    break;
                }
            }
        }
        return myAnswers;
    }

    public List<Demande> getAllMyDemand() throws SQLException {
        String myMail = app.getCurrentUser().getEmail();
        List<Demande> myDemands = new ArrayList<>();
        for (Demande demande : app.getDemandeDAO().get()){
            if (demande.getMail().equals(myMail)){
                myDemands.add(demande);
            }
        }
        return myDemands;
    }

    public void delete(Demande demandes) throws SQLException {
        int idSlot = demandes.getIdSlot();
        app.getDemandeDAO().delete(demandes.getIdDemande());
        app.getSlotDAO().delete(idSlot);

    }
}
