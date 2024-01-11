package eu.telecomnancy.directdealing.views.messaging;

import eu.telecomnancy.directdealing.model.Messaging;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

public class MessagingCell extends ListCell<Messaging>{

    @FXML
    private Label message;

    @FXML
    private Label date;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Messaging messaging, boolean b) {
        super.updateItem(messaging, b);
        if (b || messaging == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/messaging/messaging_cell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Account sender = app.getAccountDAO().get(messaging.getSender());
                Account current = app.getAccountDAO().get(app.getCurrentUser().getEmail());
                if (sender.getEmail().equals(current.getEmail())) {
                    message.setStyle("-fx-background-color: #00bfff; -fx-text-fill: white; -fx-background-radius: 10px;");
                } else {
                    message.setStyle("-fx-background-color: #f0f0f0; -fx-text-fill: black; -fx-background-radius: 10px;");
                }
                message.setText(messaging.getContent());
                date.setText(messaging.getDate().toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            setText(null);
            setGraphic(message);
        }
    }
}
