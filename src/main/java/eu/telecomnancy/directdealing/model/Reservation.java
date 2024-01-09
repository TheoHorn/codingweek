package eu.telecomnancy.directdealing.model;

import java.util.Date;

public class Reservation {
    private int idOffer;
    private String emailReserver;
    private Slot slot;
    private Date reservationDate;
    public Reservation(int idOffer, String emailReserver, int idSlot, Date reservationDate){
        this.idOffer = idOffer;
        this.emailReserver = emailReserver;
        // slot TODO
        this.reservationDate = reservationDate;
    }
}
