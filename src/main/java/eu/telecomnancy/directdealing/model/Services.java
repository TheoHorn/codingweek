package eu.telecomnancy.directdealing.model;

import javafx.scene.image.Image;

public class Services extends Content{
    public Services(String title, String category, String description, Image image) {
        super(title, category, description, image);
        super.type = 2;
    }
}
