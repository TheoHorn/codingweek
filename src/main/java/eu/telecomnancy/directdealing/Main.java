package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.database.DatabaseAccess;
import eu.telecomnancy.directdealing.views.accountlogin.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
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
    public void start(Stage stage) throws IOException {
        app = eu.telecomnancy.directdealing.model.Application.getInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/eu/telecomnancy/directdealing/views/logview/login_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}