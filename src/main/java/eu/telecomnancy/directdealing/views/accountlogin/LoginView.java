package eu.telecomnancy.directdealing.views.accountlogin;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class LoginView {
    @FXML
    public TextField mail_TextField;
    @FXML
    public TextField password_TextField;
    @FXML
    public void switchToSign(MouseEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchToSignView(event);
    }

    @FXML
    public void loginButton() throws Exception {
        String localMail = mail_TextField.getText();
        String localPassword = password_TextField.getText();
        AccountManager accountManager = new AccountManager();
        Main.currentUser = accountManager.login(localMail, localPassword);
        if (Main.currentUser != null) {
            SceneController sceneController = new SceneController();
            sceneController.switchToHome(null);
        } else {
            System.out.println("Login failed");
        }
    }

}
