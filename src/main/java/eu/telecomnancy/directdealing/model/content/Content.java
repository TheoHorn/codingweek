package eu.telecomnancy.directdealing.model.content;

import javafx.scene.image.Image;

public abstract class Content {
    private int id;
    private static int currentId;
    private String title;
    private String category;
    private String description;
    private Image image;
    private double price;
    protected int type;

    public boolean isEquipement;

    private String localisation;

    public Content(String title, String category, String description, Image image, double price) {
        this.id = currentId;
        currentId++;
        this.title = title;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocalisation() {
        return localisation;
    }

    public boolean isEquipement() {
        return isEquipement;
    }

    public void setId(int id) {
        this.id = id;
    }


}
