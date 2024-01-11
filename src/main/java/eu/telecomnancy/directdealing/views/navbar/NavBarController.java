package eu.telecomnancy.directdealing.views.navbar;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.account.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * NavBarController class
 */
public class NavBarController {
    /**
     * TextField for the search
     */
    @FXML
    public TextField search_text;
    @FXML
    public Label proposal_Label;
    /**
     * Label for the add offer
     */
    @FXML
    private Label add_offerLabel;


    @FXML
    private Label request_label;

    /**
     * Application instance
     */
    private Application app;

    /**
     * Constructor of the navbar view controller
     */
    public NavBarController(){
        this.app = Application.getInstance();
    }

    /**
     * perform the switch to the offer view
     * @throws Exception if the switch failed
     */
    @FXML
    public void switchToNewOffer() throws Exception {
        app.getSceneController().switchToOffer();

    }

    @FXML
    public void switchToRequest() throws Exception {
        // TODO
    }

    /**
     * perform the switch to the home view
     * @param event the event that trigger the switch
     * @throws Exception if the switch failed
     */
    @FXML
    public void backToHome(MouseEvent event) throws Exception {
        app.getSceneController().switchToHome();
    }

    /**
     * perform the switch to the profile view
     * @throws Exception if the switch failed
     */
    @FXML
    public void switchToProfile() throws Exception {
        app.getSceneController().switchToProfile();
    }

    /**
     * perform the switch to my proposal view
     * @throws Exception if the switch failed
     */
    @FXML
    public void switchToMyProposal() throws Exception {
        app.getSceneController().switchToMyProposal();
    }

    /**
     * log out the user
     * @throws Exception if the switch failed
     */
    @FXML
    public void logout() throws Exception {
        app.getSceneController().switchToLoginView();
    }

/**
     * perform the switch to the home view after research
     * @param event the event that trigger the switch
     * @throws Exception if the switch failed
     */
    @FXML
    public void searchUpdate(ActionEvent event) throws Exception {
        app.getResearchManager().searchOffer(search_text.getText());
        app.getSceneController().switchToHomeAfterResearch();
    }

    @FXML
    public void switchToMyDemands(MouseEvent mouseEvent) throws Exception {
        app.getSceneController().switchToMyDemands();
    }

    public void switchToReponse() throws Exception {
        app.getSceneController().switchToReponse();
    }

}
