package eu.telecomnancy.directdealing.views.accountlogin;

import eu.telecomnancy.directdealing.views.SceneController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LoginView {

    @FXML
    public void switchToSign(MouseEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchToSignView(event);
    }
}
