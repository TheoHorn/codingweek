package eu.telecomnancy.directdealing.views.navbar;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * NavBarController class
 */
public class NavBarController implements Observer {
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
    private Label florain;

    /**
     * Application instance
     */
    private final Application app;

    /**
     * Constructor of the navbar view controller
     */
    public NavBarController(){
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    /**
     * perform the switch to the offer view
     * @throws Exception if the switch failed
     */
    @FXML
    public void switchToNewOffer() throws Exception {
        app.getSceneController().switchToOffer();
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
 *
 * @throws Exception if the switch failed
 */
    @FXML
    public void searchUpdate() throws Exception {
        app.getResearchFilterManager().searchOffer(search_text.getText());
        app.getSceneController().switchToHome();
    }

    @FXML
    public void switchToMyDemands() throws Exception {
        app.getSceneController().switchToMyDemands();
    }

    public void switchToReponse() throws Exception {
        app.getSceneController().switchToReponse();
    }
    @FXML
    public void switchToConversation() throws Exception {
        app.getSceneController().swicthToConversation();
    }

    public void swicthToReclamations() throws Exception {
        app.getSceneController().switchToDispute();
    }

    @Override
    public void update() throws Exception {
        florain.setText(String.valueOf(app.getCurrentUser().getBalance()));
    }

}
