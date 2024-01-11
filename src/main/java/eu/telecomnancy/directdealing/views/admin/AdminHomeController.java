package eu.telecomnancy.directdealing.views.admin;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.dispute.Dispute;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.views.admin.dispute.AdminDisputeController;
import eu.telecomnancy.directdealing.views.admin.user.AdminUserController;
import eu.telecomnancy.directdealing.views.home.HomeViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;

public class AdminHomeController {
    public ListView<Dispute> disputesListView;
    public ListView<User> usersListView;
    public ListView<Offer> offersListView;
    private FXMLLoader mLLoader;

    @FXML
    private TabPane tabPane;
    private Application app;

    public AdminHomeController(){
        this.app = Application.getInstance();
    }

    @FXML
    public void initialize() throws Exception {
        // Initialize with the first controller (AdminDisputeController)
        mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/admin/admin_home.fxml"));
        mLLoader.setController(new AdminDisputeController(disputesListView));
        app.notifyObservers();

        app.removeAllObservers();
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            app.removeAllObservers();
            if (newTab != null) {
                try {
                    if (newTab.getText().equals("Litiges")) {
                        mLLoader.setController(new AdminDisputeController(disputesListView));
                    } else if (newTab.getText().equals("Utilisateurs")) {
                        mLLoader.setController(new AdminUserController(usersListView));
                    } else if (newTab.getText().equals("Offres")) {
                        mLLoader.setController(new HomeViewController(offersListView));
                    }
                    app.notifyObservers();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
