package eu.telecomnancy.directdealing.views.conversations;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ConversationsView implements Observer {

    @FXML
    protected ListView<String> conversationsListView;
    private final Application app;

    /**
     * Constructor of reponse view controller
     */
    public ConversationsView() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }
    /**
     * update method
     */
    @Override
    public void update() throws Exception {
        conversationsListView.getItems().clear();
        this.app.getMessagingManager().getConversers(app.getCurrentUser().getEmail()).forEach(converser -> conversationsListView.getItems().add(converser));
        conversationsListView.setCellFactory(lv -> new ConversationCell());
    }
}
