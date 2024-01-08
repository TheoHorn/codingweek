package eu.telecomnancy.directdealing.views.accountlogin;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.Main;
import javafx.event.ActionEvent;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class LoginView implements Observer {
    @FXML
    public TextField mail_TextField;
    @FXML
    public PasswordField password_TextField;
    @FXML
    public Label statusLabel;
    private Application app;

    public LoginView(Application app) {
        this.app = app;
    }

    @FXML
    public void switchToSign(MouseEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchToSignView(event);
    }

    @FXML
    public void loginButton(ActionEvent event) throws Exception {
        String localMail = mail_TextField.getText();
        String localPassword = password_TextField.getText();
        AccountManager accountManager = new AccountManager();
        Main.currentUser = accountManager.login(localMail, localPassword);
        if (Main.currentUser != null) {
            SceneController sceneController = new SceneController();
            sceneController.switchToHome(event);
        } else {
            statusLabel.setText("Mot de passe ou email incorrect");
            System.out.println("Login failed");
        }
    }

    @Override
    public void update() {
        // TODO
    }

}
