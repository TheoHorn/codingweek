package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.model.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneController {
    private final Stage stage;
    private Scene scene;

    public SceneController(Stage stage) {
        this.stage = stage;
    }

    public void switchToLoginView() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/logview/login_view.fxml"));
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Login");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignView() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/logview/signin_view.fxml"));
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Signin");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome() throws Exception{
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/home/home_view.fxml"));
        scene = new Scene(root.load());
        Application.getInstance().notifyObservers();
        stage.setTitle("TELECOM Nancy DirectDealing - Login");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToOffer() throws Exception{
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/offer/new_offer.fxml"));
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Offer");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHomeWithMouse() throws Exception{
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/home/home_view.fxml"));
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Login");
        stage.setScene(scene);
        stage.show();
    }


    public void switchToProfile() throws Exception {
        Application.getInstance().removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/profil/profil_view.fxml"));
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Profil");
        stage.setScene(scene);
        stage.show();
    }
}
