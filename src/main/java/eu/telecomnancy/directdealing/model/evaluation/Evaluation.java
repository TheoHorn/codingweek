package eu.telecomnancy.directdealing.model.evaluation;

public class Evaluation {
    private String mailEvaluator;
    private String mailEvaluated;
    private int note;
    public Evaluation(String mailEvaluator, String mailEvaluated, int note){
        this.mailEvaluator = mailEvaluator;
        this.mailEvaluated = mailEvaluated;
        this.note = note;
    }

    public String getMailEvaluator() {
        return mailEvaluator;
    }

    public String getMailEvaluated() {
        return mailEvaluated;
    }

    public int getNote() {
        return note;
    }
}
