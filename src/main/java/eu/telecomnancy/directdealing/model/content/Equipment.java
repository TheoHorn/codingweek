package eu.telecomnancy.directdealing.model.content;

import javafx.scene.image.Image;

public class Equipment extends Content{
    public Equipment(String title, String category, String description, Image image) {
        super(title, category, description, image);
        super.type = 1;
    }

}
