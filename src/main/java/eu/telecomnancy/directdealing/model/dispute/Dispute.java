package eu.telecomnancy.directdealing.model.dispute;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.demande.Demande;
import eu.telecomnancy.directdealing.model.offer.Offer;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

public class Dispute {
    private int idDispute;
    private int idDemande;
    private String content;
    private String attacker;
    private String defender;
    public Dispute(int idDispute, int idDemande, String content, String attacker, String defender) {
        this.idDispute = idDispute;
        this.idDemande = idDemande;
        this.content = content;
        this.attacker = attacker;
        this.defender = defender;
    }
    public Dispute(int idDemande, String content, String attacker, String defender) {
        this.idDemande = idDemande;
        this.content = content;
        this.attacker = attacker;
        this.defender = defender;
    }

    public int getIdDispute() {
        return idDispute;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public String getContent() {
        return content;
    }

    public String getAttacker() {
        return attacker;
    }

    public String getDefender() {
        return defender;
    }

    public Demande getDemande(){
        return app.getDemandeDAO().get(this.getIdDemande());
    }

    public Slot getSlot() throws SQLException {
        return app.getSlotDAO().get(this.getDemande().getIdSlot(), false);
    }
    public Offer getOffer() throws SQLException {
        return app.getOfferDAO().get(this.getSlot().getIdOffer());
    }

    public boolean isOwner(String mail) throws SQLException {
        return this.getOffer().getMail().equals(mail);
    }

    public Content getContentObject() throws SQLException {
        return app.getContentDAO().get(this.getOffer().getIdContent());
    }
}
