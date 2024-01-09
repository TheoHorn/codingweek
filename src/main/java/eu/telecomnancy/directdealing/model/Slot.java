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
}
