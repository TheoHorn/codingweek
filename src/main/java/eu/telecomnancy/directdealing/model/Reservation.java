package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.model.offer.Offer;

import java.sql.SQLException;
import java.util.Date;

/**
 * Reservation class
 */
public class Reservation {
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


    public Reservation(String emailReserver, int idSlot, Date reservationDate) throws SQLException {
        this.emailReserver = emailReserver;
        this.idSlot = idSlot;
        this.reservationDate = reservationDate;
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
