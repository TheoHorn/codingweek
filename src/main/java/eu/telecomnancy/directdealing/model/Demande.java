package eu.telecomnancy.directdealing.model;

import java.util.Date;

public class Demande {
    private int idDemande;
    private int idSlot;
    private String mail;
    private Date demande;
    private int status;


    public Demande(int idDemande, int idSlot, String mail, Date demande, int status) {
        this.idDemande = idDemande;
        this.idSlot = idSlot;
        this.mail = mail;
        this.demande = demande;
        this.status = status;
    }

    public Demande(int idSlot, String mail, Date demande, int status) {
        this.idSlot = idSlot;
        this.mail = mail;
        this.demande = demande;
        this.status = status;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public int getIdSlot() {
        return idSlot;
    }

    public String getMail() {
        return mail;
    }

    public Date getDemande() {
        return demande;
    }

    public int getStatus() {
        return status;
    }
}
