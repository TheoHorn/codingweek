package eu.telecomnancy.directdealing.model.evaluation;

import java.sql.SQLException;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;

public class EvaluationManager {
    public double getAverage(String mailEvaluated) throws SQLException {
        // get average of all evaluations of a user
        List<Evaluation> evaluations = app.getEvaluationDAO().get(mailEvaluated);
        double moyenne = 0;
        for(Evaluation evaluation : evaluations){
            moyenne += evaluation.getNote();
        }
        if (evaluations.size() == 0) {
            return 0;
        } else {
            return moyenne / evaluations.size();
        }
    }

    public int getEvaluationCount(String mailEvaluated) throws SQLException {
        // get number of evaluations of a user
        List<Evaluation> evaluations = app.getEvaluationDAO().get(mailEvaluated);
        return evaluations.size();
    }

    public int convert(String note){
        switch (note){
            case "1/5":
                return 1;
            case "2/5":
                return 2;
            case "3/5":
                return 3;
            case "4/5":
                return 4;
            case "5/5":
                return 5;
            default:
                return 0;
        }
    }

}
