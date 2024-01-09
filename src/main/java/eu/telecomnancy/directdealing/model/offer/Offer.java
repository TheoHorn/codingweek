package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

public abstract class Offer {
    private User owner;
    private Content content;
    private static int currentId;
    private boolean request;
    private Slot slot;
    private int idOffer;

    public Offer(User owner, Content content, Slot slot, boolean request) {
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
