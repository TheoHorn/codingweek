package eu.telecomnancy.directdealing.model;

import javafx.scene.image.Image;

import java.util.Date;

public class Equipment extends Content{
    public Equipment(String title, String category, String description, Image image) {
        super(title, category, description, image);
        super.type = 1;
    }

}
