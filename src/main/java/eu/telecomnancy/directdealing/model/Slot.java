package eu.telecomnancy.directdealing.model;

import java.sql.SQLException;
import java.util.Date;

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

    /**
     * Constructor of the slot
     * @param startTime start time of the slot
     * @param endTime end time of the slot
     * @param recurrence recurrence of the slot
     * @throws SQLException if the slot is not save in the database
     */
    public Slot(Date startTime, Date endTime, int recurrence) throws SQLException {
        // constructor for a new slot
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurrence = recurrence;
        this.id = app.getSlotDAO().save(this);
    }

    /**
     * Constructor of the slot
     * @param id id of the slot
     * @param startTime start time of the slot
     * @param endTime end time of the slot
     * @param recurrence recurrence of the slot
     */
    public Slot(int id, Date startTime, Date endTime, int recurrence){
        // constructor for a slot from the database
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurrence = recurrence;
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
}
