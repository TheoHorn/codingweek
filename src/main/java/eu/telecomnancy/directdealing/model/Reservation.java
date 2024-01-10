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
    private int idOffer;
    /**
     * Email of the reserver
     */
    private String emailReserver;
    /**
     * Slot of the reservation
     */
    private int idSlot;
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
    public Reservation(int idOffer, String emailReserver, int idSlot, Date reservationDate) throws SQLException {
        this.idOffer = idOffer;
        this.emailReserver = emailReserver;
        this.idSlot = idSlot;
        this.reservationDate = reservationDate;
    }

    public int getIdOffer() {
        return idOffer;
    }

    public String getEmailReserver() {
        return emailReserver;
    }

    public void setEmailReserver(String emailReserver) {
        this.emailReserver = emailReserver;
    }

    public int getIdSlot() {
        return idSlot;
    }

    public void setIdSlot(int idSlot) {
        this.idSlot = idSlot;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
