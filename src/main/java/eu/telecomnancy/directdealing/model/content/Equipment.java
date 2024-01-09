package eu.telecomnancy.directdealing.model.content;

import javafx.scene.image.Image;

public class Equipment extends Content{
    public Equipment(String title, String category, String description, Image image, double price) {
        super(title, category, description, image, price);
        super.type = 1;
    }

}
