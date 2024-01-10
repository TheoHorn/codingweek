package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.model.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * SceneController class
 */
public class SceneController {
    /**
     * Stage of the application
     */
    private final Stage stage;
    /**
     * Scene of the application
     */
    private Scene scene;

    /**
     * Constructor of the scene controller
     * @param stage the stage of the application
     */
    public SceneController(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    /**
     * perform the switch to the login view
     * @throws Exception if the switch failed
     */
    public void switchToLoginView() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/logview/login_view.fxml"));
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - LogIn");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * perform the switch to the sign in view
     * @throws Exception if the switch failed
     */
    public void switchToSignView() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/logview/signin_view.fxml"));
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - SignIn");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * perform the switch to the home view
     * @throws Exception if the switch failed
     */
    public void switchToHome() throws Exception{
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/home/home_view.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Home");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * perform the switch to the offer view
     * @throws Exception if the switch failed
     */
    public void switchToOffer() throws Exception{
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/offer/new_offer.fxml"));
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Offer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * perform the switch to the profile view
     * @throws Exception if the switch failed
     */
    public void switchToProfile() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/profil/profil_view.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Profil");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * perform the switch to my proposal view
     * @throws Exception if the switch failed
     */
    public void switchToMyProposal() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/proposal/my_proposal_view.fxml"));
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - My proposal");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToOfferDisplay() throws Exception{
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/offer/offer_display.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Offer");
        stage.setScene(scene);
        stage.show();
    }
}
