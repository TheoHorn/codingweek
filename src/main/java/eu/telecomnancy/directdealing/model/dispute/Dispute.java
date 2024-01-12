package eu.telecomnancy.directdealing.model.dispute;

import eu.telecomnancy.directdealing.model.account.User;

public class Dispute {
    private int idDispute;
    private int idDemande;
    private String content;
    private String attacker;
    private String defender;
    public Dispute(int idDispute, int idDemande, String content, String attacker, String defender) {
        this.idDispute = idDispute;
        this.idDemande = idDemande;
        this.content = content;
        this.attacker = attacker;
        this.defender = defender;
    }
    public Dispute(int idDemande, String content, String attacker, String defender) {
        this.idDemande = idDemande;
        this.content = content;
        this.attacker = attacker;
        this.defender = defender;
    }

    public int getIdDispute() {
        return idDispute;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public String getContent() {
        return content;
    }

    public String getAttacker() {
        return attacker;
    }

    public String getDefender() {
        return defender;
    }
}
