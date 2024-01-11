package eu.telecomnancy.directdealing.views.messaging;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MessagingController implements Observer {

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Label messageLabel;


    private Application app;
    public MessagingController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    @FXML
    public void sendMessage() throws Exception {
        // TODO
    }

    @Override
    public void update() throws Exception {

    }
}
