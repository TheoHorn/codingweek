package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.content.Content;

import java.util.ArrayList;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;

/**
 * SearchBar class
 */
public class ResearchManager {

    List<Offer> researchedOffers;
    List<Offer> filteredOffers;

    public ResearchManager(){
        this.researchedOffers = new ArrayList<>();
        this.filteredOffers = new ArrayList<>();
    }

    public void resetOffers() throws Exception {
        this.researchedOffers = app.getOffers();
        this.filteredOffers = app.getOffers();
    }
    public List<Offer> getResearchedOffers() {
        if (this.researchedOffers == null){
            try {
                this.resetOffers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return researchedOffers;
    }

    public List<Offer> getFilteredOffers() {
        if (this.filteredOffers == null){
            try {
                this.resetOffers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filteredOffers;
    }

    public void setResearchedOffers(List<Offer> researchedOffers) {
        this.researchedOffers = researchedOffers;
    }

    /**
     * Search the string in the offers
     * @param offers List of the offers
     * @return List of the offers which contains the string
     */
    public void searchOffer(String motRecherche) throws Exception {
        this.resetOffers();
        List<Offer> result = new ArrayList<>();
        for (Offer offer : this.researchedOffers){
            String info = "";
            Content ctn = app.getContentDAO().get(offer.getIdContent());
            User user = (User)app.getAccountDAO().get(offer.getMail());
            info += ctn.getTitle() + " " + ctn.getCategory() + " " + ctn.getLocalisation() + " " + ctn.getCategory() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail();
            if (info.toUpperCase().contains(motRecherche.toUpperCase())){
                result.add(offer);
            }
        }
        this.researchedOffers = result;
    }

    public void filterOffersByDate(){
        return ;
    }

    public void filterOffersByPrice(String price) throws Exception {
        double price_value = switch (price) {
            case "Moins de 5€" -> 5.0;
            case "Moins de 10€" -> 10.0;
            case "Moins de 20€" -> 20.0;
            case "Moins de 50€" -> 50.0;
            case "Moins de 100€" -> 100.0;
            case "Plus de 100€" -> 999999.99;
            default -> 0.0;
        };
        List<Offer> result = new ArrayList<>();
        for(Offer o: this.researchedOffers){
            Content c = app.getContentDAO().get(o.getIdContent());
            if(c.getPrice() <= price_value){
                result.add(o);
            }
        }
        System.out.println(result);
        this.filteredOffers= result;
    }


    public void filterOffersByCategory(){
        return ;
    }

    public void filterOffersByLocation(){
        return ;
    }


    public void resetFilter() {
        this.filteredOffers = this.researchedOffers;
    }
}
