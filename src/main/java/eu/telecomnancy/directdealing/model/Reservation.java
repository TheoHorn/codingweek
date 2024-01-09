package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.database.SlotManager;

import java.sql.SQLException;
import java.util.Date;

public class Reservation {
    private int idOffer;
    private String emailReserver;
    private Slot slot;
    private Date reservationDate;
    public Reservation(int idOffer, String emailReserver, Slot slot, Date reservationDate) throws SQLException {
        this.idOffer = idOffer;
        this.emailReserver = emailReserver;
        this.slot = slot;
        this.reservationDate = reservationDate;
    }
}
