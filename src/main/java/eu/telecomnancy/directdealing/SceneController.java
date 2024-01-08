package eu.telecomnancy.directdealing;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLoginView(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/eu/telecomnancy/directdealing/views/accountcreating/connexion_account.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("TELECOM Nancy DirectDealing - Login");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignView(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/eu/telecomnancy/directdealing/views/accountcreating/create_account.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("TELECOM Nancy DirectDealing - Login");
        stage.setScene(scene);
        stage.show();
    }


}
