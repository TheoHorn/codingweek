package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.database.DatabaseAccess;
import eu.telecomnancy.directdealing.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAction;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
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
        //User user = new User("larousse", "adrien", "bonjour.la@mail.com",0.5, false);
        //accountManager.addUser(user);
        // DatabaseAccess.disconnectFromDatabase();
        //User user2 = (User)accountManager.getAccount("ad2.la@mail.com");
        // System.out.println(user2.toString());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/accountcreating/create_account.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}