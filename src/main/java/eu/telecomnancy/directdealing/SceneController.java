package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.views.logview.SignInViewController;
import eu.telecomnancy.directdealing.views.logview.LoginViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene scene;

    public void switchToLoginView(MouseEvent event) throws Exception {
        Main.app.removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("views/logview/login_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Login");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignView(MouseEvent event) throws Exception {
        Main.app.removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("views/logview/signin_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Signin");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws Exception{
        Main.app.removeAllObservers();
        FXMLLoader root = new FXMLLoader(getClass().getResource("views/home/home_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Login");
        stage.setScene(scene);
        stage.show();
    }


}
