package eu.telecomnancy.directdealing.views.admin;

import eu.telecomnancy.directdealing.model.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static eu.telecomnancy.directdealing.Main.app;

public class navBarController implements Observer {

    @Override
    public void update() throws Exception {
        // TODO
    }

    @FXML
    public void switchToLogin() throws Exception {
        app.getSceneController().switchToLoginView();

    }
}
