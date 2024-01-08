package eu.telecomnancy.directdealing.views.accountlogin;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.Main;
import javafx.event.ActionEvent;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginView implements Observer {
    private Application app;

    public LoginView(Application app) {
        this.app = app;
    }

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

    @Override
    public void update() {
        // TODO
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
            System.out.println("Login failed");
        }
    }

}
