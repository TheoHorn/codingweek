package eu.telecomnancy.directdealing.views.accountlogin;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LoginView implements Observer {
    private Application app;

    public LoginView(Application app) {
        this.app = app;
    }

    @FXML
    public void switchToSign(MouseEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchToSignView(event);
    }

    @Override
    public void update() {
        // TODO
    }
}
