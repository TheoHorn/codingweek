package eu.telecomnancy.directdealing.model;

import java.util.Date;

public class Slot {
    private int id;
    private Date startTime;
    private Date endTime;
    private int recurrence;

    public Slot(int id, Date startTime, Date endTime, int recurrence){
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
