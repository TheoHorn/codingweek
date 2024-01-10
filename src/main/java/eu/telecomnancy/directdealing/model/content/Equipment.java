package eu.telecomnancy.directdealing.model.content;

import javafx.scene.image.Image;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

public class Equipment extends Content{
    public Equipment(String title, String category, String description, Image image, double price) throws SQLException {
        super(title, category, description, image, price);
        super.type = 1;
        System.out.println("id : " + app.getContentDAO().save(this));
        super.setId(app.getContentDAO().save(this));
    }

    public Equipment(int idEquipement, String title, String category, String description, Image image, double price) {
        super(title, category, description, image, price);
        super.type = 1;
    }

}
