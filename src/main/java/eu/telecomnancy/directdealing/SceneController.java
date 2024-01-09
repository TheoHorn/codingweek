package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.views.accountcreating.AccountCreatingController;
import eu.telecomnancy.directdealing.views.accountlogin.LoginView;
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
        FXMLLoader root = new FXMLLoader(getClass().getResource("views/accountcreating/connexion_account.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Login");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignView(MouseEvent event) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("views/accountcreating/create_account.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Signin");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws Exception{
        FXMLLoader root = new FXMLLoader(getClass().getResource("views/home/home_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setTitle("TELECOM Nancy DirectDealing - Login");
        stage.setScene(scene);
        stage.show();
    }


}
