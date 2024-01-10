package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

public abstract class Offer {
    /**
     * Owner of the offer
     */
    private int idOffer;
    private String mail;
    private boolean isRequest;
    private int idContent;
    private int idSlot;
    /**
     * Content of the offer
     */
    /**
     * boolean to know if the offer is a request or a proposal
     */
    /**
     * slot of the offer
     */
    /**
     * id of the offer
     */


    public Offer(int idOffer, String mail, boolean isRequest, int idContent, int idSlot) throws SQLException {
        this.idOffer = idOffer;
        this.mail = mail;
        this.isRequest = isRequest;
        this.idContent = idContent;
        this.idSlot = idSlot;
    }

    public Offer(String mail, boolean isRequest, int idContent, int idSlot) throws SQLException {
        this.mail = mail;
        this.isRequest = isRequest;
        this.idContent = idContent;
        this.idSlot = idSlot;
        this.idOffer = app.getOfferDAO().save(this);
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getIdContent() {
        return idContent;
    }

    public void setIdContent(int idContent) {
        this.idContent = idContent;
    }

    public int getIdSlot() {
        return idSlot;
    }

    public boolean isRequest() {
        return isRequest;
    }


    public int getIdOffer(){
        return this.idOffer;
    }

}
