package eu.telecomnancy.directdealing.model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static eu.telecomnancy.directdealing.Main.app;

/**
 * Slot class
 */
public class Slot {
    /**
     * id of the slot
     */
    private int id;
    /**
     * start time of the slot
     */
    private Date startTime;
    /**
     * end time of the slot
     */
    private Date endTime;
    /**
     * recurrence of the slot
     */
    private int recurrence;

    private int idOffer;

    /**
     * Constructor of the slot
     * @param startTime start time of the slot
     * @param endTime end time of the slot
     * @param recurrence recurrence of the slot
     * @throws SQLException if the slot is not save in the database
     */
    public Slot(Date startTime, Date endTime, int recurrence, int idOffer) throws SQLException {
        // constructor for a new slot
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurrence = recurrence;
        this.id = app.getSlotDAO().save(this);
        this.idOffer = idOffer;
    }

    /**
     * Constructor of the slot
     * @param id id of the slot
     * @param startTime start time of the slot
     * @param endTime end time of the slot
     * @param recurrence recurrence of the slot
     */
    public Slot(int id, Date startTime, Date endTime, int recurrence, int idOffer){
        // constructor for a slot from the database
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurrence = recurrence;
        this.idOffer = idOffer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(int recurrence) {
        this.recurrence = recurrence;
    }

    public String getDisponibility() {
        return startTime.toString() + " - " + endTime.toString();
    }

    public int getIdOffer() {
        return idOffer;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm");
        if (this.recurrence != 0) {
            return "Tous les " + recurrence + " jours du " + startTime.toString() + " au " + endTime.toString();
        } else if (this.startTime == null) {
            return "Null";
        } else if (this.endTime == null) {
            return "Le " + formatter.format(startTime);
        } else {
            return "Du " + formatter.format(startTime) + " au " + formatter.format(endTime);
        }
    }
}
