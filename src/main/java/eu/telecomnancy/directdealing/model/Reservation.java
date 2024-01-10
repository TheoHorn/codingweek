package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.model.offer.Offer;

import java.sql.SQLException;
import java.util.Date;

/**
 * Reservation class
 */
public class Reservation {
    /**
     * Offer of the reservation
     */
    private Offer offer;
    /**
     * Email of the reserver
     */
    private String emailReserver;
    /**
     * Slot of the reservation
     */
    private Slot slot;
    /**
     * Date of the reservation
     */
    private Date reservationDate;

    /**
     * Constructor of the reservation
     * @param offer Offer of the reservation
     * @param emailReserver Email of the user who reserve
     * @param slot Slot of the reservation
     * @param reservationDate Date of the reservation
     * @throws SQLException if the reservation is not save in the database
     */
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
