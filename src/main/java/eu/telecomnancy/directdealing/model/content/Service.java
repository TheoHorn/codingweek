package eu.telecomnancy.directdealing.model.content;

import eu.telecomnancy.directdealing.database.ContentManager;
import javafx.scene.image.Image;

import java.sql.SQLException;

public class Service extends Content{
    private int id;
    public Service(String title, String category, String description, Image image, double price) throws SQLException {
        super(title, category, description, image, price);
        super.type = 2;
        super.setId(ContentManager.addContent(this));
    }
    public Service(int idService, String title, String category, String description, Image image, double price) {
        super(title, category, description, image, price);
        super.type = 2;
    }
}
