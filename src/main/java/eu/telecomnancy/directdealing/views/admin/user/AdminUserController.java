package eu.telecomnancy.directdealing.views.admin.user;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.account.User;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class AdminUserController implements Observer {
    /**
     * ListView of the offers
     */
    @FXML
    protected ListView<User> usersListView;
    /**
     * Application instance
     */
    private Application app;

    public AdminUserController(){
        this.app = Application.getInstance();
        app.addObserver(this);
    }


    @Override
    public void update() throws Exception {
        usersListView.getItems().clear();
        this.app.getUsers().forEach(user -> usersListView.getItems().add(user));
        usersListView.setCellFactory(lv -> new UserCell());
    }
}
