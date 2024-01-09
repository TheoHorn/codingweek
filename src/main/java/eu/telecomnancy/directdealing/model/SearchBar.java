package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.content.Content;

import java.util.ArrayList;

public class SearchBar {

    private String motRecherche;

    public SearchBar(){
        this.motRecherche = "";
    }

    public SearchBar(String motRecherche){
        this.motRecherche = motRecherche;
    }

    public void setMotRecherche(String motRecherche){
        this.motRecherche = motRecherche;
    }
    public String getMotRecherche() {
        return motRecherche;
    }

    public ArrayList<Offer> search(ArrayList<Offer> offers){
        ArrayList<Offer> result = new ArrayList<>();
        for (Offer offer : offers){
            String info = "";
            Content ctn = offer.getContent();
            User user = offer.getOwner();
            info += ctn.getTitle() + " " + ctn.getCategory() + " " + ctn.getLocalisation() + " " + ctn.getCategory() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail();
            if (info.contains(motRecherche)){
                result.add(offer);
            }
        }
        return result;
    }

}
