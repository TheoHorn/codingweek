package eu.telecomnancy.directdealing.views.conversations;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.demande.Demande;
import eu.telecomnancy.directdealing.model.messaging.Messaging;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuButton;

import java.sql.SQLException;
import java.util.List;
import static eu.telecomnancy.directdealing.Main.app;

public class ConversationCell extends ListCell<String> {
    @FXML
    private Label idLabel;
    @FXML
    private Label messagingLabel;
    /**
     * FXMLLoader
     */
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(String converser, boolean b) {
        super.updateItem(converser, b);
        if (b || converser == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/conversations/conversation_cell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Account converserAccount = app.getAccountDAO().get(converser);
                this.idLabel.setText(converserAccount.getLastName() + " " + converserAccount.getFirstName());
                List<Messaging> messagingList = app.getMessagingManager().getConversationMessage(converser);
                this.messagingLabel.setText(messagingList.get((messagingList.size())-1).getContent());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            setText(null);
            setGraphic(mLLoader.getRoot());
        }
    }

    @FXML
    public void displayConversation() throws Exception {
        Application.getInstance().setLastAccount(app.getAccountDAO().get(getItem()));
        app.getSceneController().switchtoMessaging();
    }


}
