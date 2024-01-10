package eu.telecomnancy.directdealing.views.logview;

import javafx.event.ActionEvent;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * LogInViewController class
 */
public class LogInViewController implements Observer {
    /**
     * TextField for the mail
     */
    @FXML
    private TextField mail_TextField;
    /**
     * TextField for the password
     */
    @FXML
    private PasswordField password_TextField;
    /**
     * Label for the status
     */
    @FXML
    private Label statusLabel;
    /**
     * Application instance
     */
    private Application app;

    /**
     * Constructor of the login view controller
     */
    public LogInViewController() {
        this.app = Application.getInstance();
    }

    /**
     * perform the switch to the sign view
     * @param event the event that trigger the switch
     * @throws Exception if the switch failed
     */
    @FXML
    public void switchToSign(MouseEvent event) throws Exception {
        app.getSceneController().switchToSignView();
    }

    /**
     * perform the login
     * @param event the event that trigger the login
     * @throws Exception if the login failed
     */
    @FXML
    public void loginButton(ActionEvent event) throws Exception {
        try {
            app.login(mail_TextField.getText(), password_TextField.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            statusLabel.setText(e.getMessage());
            System.out.println("Login failed");
        }
    }

    @Override
    public void update() {
        // TODO
    }

}
