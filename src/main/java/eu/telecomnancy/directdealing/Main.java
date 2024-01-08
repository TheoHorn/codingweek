package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.database.DatabaseAccess;
import eu.telecomnancy.directdealing.model.Account;
import eu.telecomnancy.directdealing.model.User;
import eu.telecomnancy.directdealing.views.accountcreating.AccountCreatingController;
import eu.telecomnancy.directdealing.views.accountlogin.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAction;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    public static Account currentUser;
    public static eu.telecomnancy.directdealing.model.Application app;
    @Override
    public void init() throws SQLException, IOException {
        DatabaseAccess.connectToDatabase();
    }
    @Override
    public void stop() throws SQLException {
        DatabaseAccess.disconnectFromDatabase();
    }
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        AccountManager accountManager = new AccountManager();
        app = new eu.telecomnancy.directdealing.model.Application(currentUser);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/accountcreating/connexion_account.fxml"));
        fxmlLoader.setControllerFactory(iC -> new LoginView(app));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}