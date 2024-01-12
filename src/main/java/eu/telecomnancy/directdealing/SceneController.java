package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.model.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - LogIn");
        stage.setScene(scene);
        stage.centerOnScreen();
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
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - SignIn");
        stage.setScene(scene);
        stage.centerOnScreen();
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
        Application.getInstance().getResearchFilterManager().initialize_visible_offers();
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Home");
        stage.setScene(scene);
        stage.centerOnScreen();
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
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Offer");
        stage.setScene(scene);
        stage.centerOnScreen();
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
        stage.centerOnScreen();
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
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - My proposal");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToOfferDisplay() throws Exception{
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/offer/offer_display.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Offer");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

     public void openReservationPopup() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/offer/reservation_popup.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Reservation");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToMyDemands() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/demand/demand_view.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Mes demandes");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    public void switchToReponse() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/reponse/reponse_view.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Mes réponses");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToProfileDisplay() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/profil/foreign_profil_view.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Profil Utilisateur");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchtoMessaging() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/messaging/messaging_view.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Messagerie");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void swicthToConversation() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/conversations/conversations_view.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Mes conversations");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switchToAdminHome() throws Exception{
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/admin/admin_home.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Administrateur");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDispute() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/dispute/dispute_view.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Mes réclamations");
        stage.setScene(scene);
        stage.show();
    }
}
