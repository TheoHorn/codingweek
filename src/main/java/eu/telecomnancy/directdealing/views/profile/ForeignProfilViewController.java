package eu.telecomnancy.directdealing.views.profile;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.evaluation.Evaluation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class ForeignProfilViewController implements Observer {
    @FXML
    private Label nameLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label mailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label moyenneLabel;
    @FXML
    private ChoiceBox valueEvaluation;
    @FXML
    private Button evaluateButton;
    private Application app;
    public ForeignProfilViewController(){
        this.app = Application.getInstance();
        this.app.addObserver(this);

    }

    @Override
    public void update() throws SQLException {
        this.nameLabel.setText(app.getLastAccount().getLastName());
        this.prenomLabel.setText(app.getLastAccount().getFirstName());
        this.mailLabel.setText(app.getLastAccount().getEmail());
        this.addressLabel.setText(app.getLastAccount().getLocalisation());
        Evaluation userEvaluation = app.getEvaluationDAO().get(app.getCurrentUser().getEmail(), app.getLastAccount().getEmail());
        String commentaire;
        if(userEvaluation != null){
            commentaire = "(Ma note : "+userEvaluation.getNote()+")";
            valueEvaluation.setValue(userEvaluation.getNote()+"/5");
        } else {
            commentaire = "(Pas encore noté)";
            valueEvaluation.setValue("3/5");

        }
        this.moyenneLabel.setText(String.valueOf(app.getEvaluationManager().getAverage(app.getLastAccount().getEmail()))+"/5 "+ "("+app.getEvaluationManager().getEvaluationCount(app.getLastAccount().getEmail())+") " +commentaire);

    }

    public void sendEvaluation() throws Exception {
        // Update the evaluation :
        Evaluation evaluation = new Evaluation(app.getCurrentUser().getEmail(), app.getLastAccount().getEmail(), app.getEvaluationManager().convert((String) valueEvaluation.getValue()));
        app.getEvaluationDAO().save(evaluation);

        app.sendEvaluation(app.getCurrentUser().getEmail(), app.getLastAccount().getEmail(), this.valueEvaluation.getValue().toString());
    }

    @FXML
    public void goMessage() throws Exception {
        app.getSceneController().switchtoMessaging();
    }
}
