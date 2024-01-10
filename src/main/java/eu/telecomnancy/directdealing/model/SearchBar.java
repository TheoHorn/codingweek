package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.content.Content;

import java.sql.SQLException;
import java.util.ArrayList;

import static eu.telecomnancy.directdealing.Main.app;

/**
 * SearchBar class
 */
public class SearchBar {

    /**
     * String to search
     */
    private String motRecherche;

    /**
     * Constructor of the SearchBar
     */
    public SearchBar(){
        this.motRecherche = "";
    }

    /**
     * Constructor of the SearchBar
     * @param motRecherche String to search
     */
    public SearchBar(String motRecherche){
        this.motRecherche = motRecherche;
    }

    public void setMotRecherche(String motRecherche){
        this.motRecherche = motRecherche;
    }
    public String getMotRecherche() {
        return motRecherche;
    }

    /**
     * Search the string in the offers
     * @param offers List of the offers
     * @return List of the offers which contains the string
     */
    public ArrayList<Offer> search(ArrayList<Offer> offers) throws SQLException {
        ArrayList<Offer> result = new ArrayList<>();
        for (Offer offer : offers){
            String info = "";
            Content ctn = app.getContentDAO().get(offer.getIdContent());
            User user = (User)app.getAccountDAO().get(offer.getMail());
            info += ctn.getTitle() + " " + ctn.getCategory() + " " + ctn.getLocalisation() + " " + ctn.getCategory() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail();
            if (info.contains(motRecherche)){
                result.add(offer);
            }
        }
        return result;
    }

}
