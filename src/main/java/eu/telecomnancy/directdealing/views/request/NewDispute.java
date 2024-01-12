package eu.telecomnancy.directdealing.views.request;

import eu.telecomnancy.directdealing.model.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NewDispute {
    @FXML
    private TextArea description;
    private Application app;
    private String attacker;
    private String defender;

    public NewDispute(String attacker, String defender) {
        this.app = Application.getInstance();
        this.attacker = attacker;
        this.defender = defender;
    }

    @FXML
    public void claim() throws Exception {
        this.app.sendNewReclamation(attacker, defender, description.getText());
        this.app.getSceneController().switchToHome();
    }
}
