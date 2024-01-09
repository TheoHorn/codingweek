package eu.telecomnancy.directdealing.views.navbar;

import eu.telecomnancy.directdealing.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class NavBarController {
    @FXML
    private Label add_offerLabel;

    @FXML
    public void switchToNewOffer(MouseEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchToOffer(event);
    }

}
