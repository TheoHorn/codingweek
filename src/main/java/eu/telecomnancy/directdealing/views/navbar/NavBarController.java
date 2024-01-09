package eu.telecomnancy.directdealing.views.navbar;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.model.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class NavBarController {
    private Application app;
    public NavBarController(){
        this.app = Application.getInstance();
    }
    @FXML
    private Label add_offerLabel;

    @FXML
    public void switchToNewOffer(MouseEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchToOffer(event);
    }

    @FXML
    public void backToHome(MouseEvent event) throws Exception {
        System.out.println("hello");
        SceneController sceneController = new SceneController();
        sceneController.switchToHomeWithMouse(event);
    }

    @FXML
    public void switchToProfile(ActionEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchToProfile(event);
    }
}
