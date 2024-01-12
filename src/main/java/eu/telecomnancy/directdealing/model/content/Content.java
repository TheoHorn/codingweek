package eu.telecomnancy.directdealing.model.content;

import java.io.File;

public abstract class Content {
    /**
     * id of the content
     */
    private int idContent;
    /**
     * title of the content
     */
    private String title;
    /**
     * category of the content
     */
    private String category;
    /**
     * description of the content
     */
    private String description;
    /**
     * file of the content
     */
    private File image;
    /**
     * price of the content
     */
    private double price;
    /**
     * boolean to know if the content is an equipment
     */
    public boolean isEquipment;
    /**
     * localisation of the content
     */
    private String localisation;

    /**
     * Constructor of the content
     * @param title Title of the content
     * @param category Category of the content
     * @param description Description of the content
     * @param image Image of the content
     * @param price Price of the content
     */
    public Content(String title, String category, String description, File image, double price, String localisation) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
        this.localisation = localisation;
    }


    public int getIdContent() {
        return idContent;
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

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
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

    public boolean isEquipment() {
        return isEquipment;
    }

    public void setIdContent(int idContent) {
        this.idContent = idContent;
    }


}
