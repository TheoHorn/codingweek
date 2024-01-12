package eu.telecomnancy.directdealing.model.offer;

import java.sql.SQLException;

public abstract class Offer {
    /**
     * Owner of the offer
     */
    private int idOffer;
    private String mail;
    private boolean isRequest;
    private int idContent;
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


    public Offer(int idOffer, String mail, boolean isRequest, int idContent) throws SQLException {
        this.idOffer = idOffer;
        this.mail = mail;
        this.isRequest = isRequest;
        this.idContent = idContent;
    }

    public Offer(String mail, boolean isRequest, int idContent) throws SQLException {
        this.mail = mail;
        this.isRequest = isRequest;
        this.idContent = idContent;
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

    public boolean isRequest() {
        return isRequest;
    }

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
    }

    public int getIdOffer(){
        return this.idOffer;
    }
}
