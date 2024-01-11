package eu.telecomnancy.directdealing.views.admin;

import eu.telecomnancy.directdealing.views.admin.dispute.AdminDisputeController;
import eu.telecomnancy.directdealing.views.admin.offer.AdminOfferController;
import eu.telecomnancy.directdealing.views.admin.user.AdminUserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;

public class AdminHomeController {
    private FXMLLoader mLLoader;
    private final AdminUserController adminUserController;
    private final AdminOfferController adminOfferController;
    private final AdminDisputeController adminDisputeController;

    @FXML
    private TabPane tabPane;

    public AdminHomeController(){
        this.adminDisputeController = new AdminDisputeController();
        this.adminOfferController = new AdminOfferController();
        this.adminUserController = new AdminUserController();
    }
    @FXML
    public void initialize() {
        mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/home/offer_cell.fxml"));
        mLLoader.setController(adminDisputeController);
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            // code to execute when changing slides
            if (newTab != null) {
                if (newTab.getText().equals("Litiges")){
                    mLLoader.setController(adminDisputeController);
                }
                else if (newTab.getText().equals("Utilisateurs")){
                    mLLoader.setController(adminUserController);
                }
                else if (newTab.getText().equals("Offres")){
                    mLLoader.setController(adminOfferController);
                }
            }
        });
    }
}
