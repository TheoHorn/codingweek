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
    public void switchToNewOffer() throws Exception {
//        SceneController sceneController = new SceneController();
//        sceneController.switchToOffer(event);
        app.getSceneController().switchToOffer();
    }

    @FXML
    public void backToHome() throws Exception {
        System.out.println("hello");
//        SceneController sceneController = new SceneController();
//        sceneController.switchToHomeWithMouse(event);
        app.getSceneController().switchToHome();
    }

    @FXML
    public void switchToProfile() throws Exception {
        app.getSceneController().switchToProfile();
    }
}
