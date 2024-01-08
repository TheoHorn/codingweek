package eu.telecomnancy.directdealing.views.accountcreating;

import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.model.User;
import eu.telecomnancy.directdealing.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class AccountCreatingController {
    @FXML
    public TextField mail_textfield;
    @FXML
    public TextField password_textfield;
    @FXML
    public TextField firstname_textfield;
    @FXML
    public TextField lastname_textfield;
    @FXML
    public TextField password_confirm_textfield;
    @FXML
    public Button creationButton;

    public void pressCreationButton() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        AccountManager accountManager = new AccountManager();
        String localMail = this.mail_textfield.getText().toString();
        String localPassword = this.password_textfield.getText().toString();
        String localLastname = this.lastname_textfield.getText().toString();
        String localFirstname = this.firstname_textfield.getText().toString();
        String localPasswordConfirm = this.password_confirm_textfield.getText().toString();
        System.out.println(localMail);
        if (!localMail.equals("") && !localPassword.equals("") && !localLastname.equals("") && !localFirstname.equals("") && !localPasswordConfirm.equals("")){
            System.out.println(!accountManager.isSave(localMail));
            if (!accountManager.isSave(localMail)){
                User user = new User(localLastname,localFirstname,localMail,500.0, false,localPassword);
                accountManager.addUser(user);
                System.out.println("[Debug:AccountCreatingController] Succesfull");
            }
        }
        System.out.println("creationButton pressed");
    }

    @FXML
    public void switchToLoginPage(MouseEvent mouseEvent) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchToLoginView(mouseEvent);
    }
}
