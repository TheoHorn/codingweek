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
        User user = new User("larousse", "adrien", "ad.la@mail.com",0.5, false);
        accountManager.addUser(user);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}