package eu.telecomnancy.directdealing.model.messaging;

import java.util.Date;

public class Messaging {

    private int idMsg;

    private String content;

    private String sender;

    private String receiver;

    private Date date;

    public Messaging(int idMsg, String content, String sender, String receiver, Date date) {
        this.idMsg = idMsg;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
    }

    public Messaging(String content, String sender, String receiver, Date date) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
    }

    public int getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(int idMsg) {
        this.idMsg = idMsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
