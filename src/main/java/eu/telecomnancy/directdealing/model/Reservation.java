package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.model.offer.Offer;

import java.sql.SQLException;
import java.util.Date;

public class Reservation {
    private Offer offer;
    private String emailReserver;
    private Slot slot;
    private Date reservationDate;
    public Reservation(Offer offer, String emailReserver, Slot slot, Date reservationDate) throws SQLException {
        this.offer = offer;
        this.emailReserver = emailReserver;
        this.slot = slot;
        this.reservationDate = reservationDate;
    }

    public Offer getOffer() {
        return offer;
    }



    public String getEmailReserver() {
        return emailReserver;
    }

    public void setEmailReserver(String emailReserver) {
        this.emailReserver = emailReserver;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }


}
