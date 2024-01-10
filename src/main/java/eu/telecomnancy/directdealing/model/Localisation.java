package eu.telecomnancy.directdealing.model;

/**
 * Localisation class
 */
public class Localisation {
    /**
     * Longitude coordinate of the localisation
     */
    private double longitudeCoord;
    /**
     * Latitude coordinate of the localisation
     */
    private double latitudeCoord;

    /**
     * Constructor of the localisation
     * @param longitudeCoord Longitude coordinate of the localisation
     * @param latitudeCoord Latitude coordinate of the localisation
     */
    public Localisation(double longitudeCoord, double latitudeCoord){
        this.longitudeCoord = longitudeCoord;
        this.latitudeCoord = latitudeCoord;
    }
}
