package eu.telecomnancy.directdealing.views.messaging;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import eu.telecomnancy.directdealing.model.messaging.Messaging;

public class MessagingController implements Observer {

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Label messageLabel;

    @FXML
    private ListView<Messaging> messagingListView;


    private Application app;
    public MessagingController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    @FXML
    public void sendMessage() throws Exception {
        if (!messageTextArea.getText().isEmpty()){
            app.getMessagingManager().sendMessage(messageTextArea.getText(), app.getLastAccount().getEmail());
        }
        messageTextArea.clear();
    }

    @Override
    public void update() throws Exception {
        messageLabel.setText(app.getLastAccount().getFirstName() + " " + app.getLastAccount().getLastName());
        messagingListView.getItems().clear();
        this.app.getMessagingManager().getMessaging().forEach(messaging -> messagingListView.getItems().add(messaging));
        messagingListView.setCellFactory(lv -> new MessagingCell());
        messagingListView.scrollTo(messagingListView.getItems().size()-1);
    }
}
