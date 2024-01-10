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
    private User owner;
    /**
     * Content of the offer
     */
    private Content content;
    /**
     * current id
     */
    private static int currentId;
    /**
     * boolean to know if the offer is a request or a proposal
     */
    private boolean request;
    /**
     * slot of the offer
     */
    private Slot slot;
    /**
     * id of the offer
     */
    private int idOffer;

    /**
     * Constructor of the offer
     * @param owner Owner of the offer
     * @param content Content of the offer
     * @param slot Slot of the offer
     * @param request Boolean to know if the offer is a request or a proposal
     * @throws SQLException if the offer is not save in the database
     */
    public Offer(User owner, Content content, Slot slot, boolean request) throws SQLException {
        this.request = request;
        this.slot = slot;
        this.owner = owner;
        this.content = content;
        this.idOffer = app.getOfferDAO().save(this);
    }

    public Offer(int idOffer, User owner, Content content, Slot slot, boolean request) {
        this.idOffer = idOffer;
        this.request = request;
        this.slot = slot;
        this.owner = owner;
        this.content = content;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getIdOffer(){
        return this.idOffer;
    }

    public Slot getSlot() {
        return this.slot;
    }

    public boolean isRequest(){
        return this.request;
    }
}
