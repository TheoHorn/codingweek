package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.database.SlotManager;

import java.sql.SQLException;
import java.util.Date;

public class Slot {
    private int id;
    private Date startTime;
    private Date endTime;
    private int recurrence;

    public Slot(Date startTime, Date endTime, int recurrence) throws SQLException {
        // constructor for a new slot
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurrence = recurrence;
        this.id = SlotManager.addSlot(startTime, endTime, recurrence);
    }

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
