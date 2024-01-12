package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.evaluation.EvaluationManager;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.content.Content;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;

/**
 * SearchBar class
 */
public class ResearchFilterManager {

    List<Offer> all_offers_visible;
    List<Offer> researchedOffers;
    List<Offer> filteredOffers;

    private String search_text;

    public ResearchFilterManager() {
        this.researchedOffers = new ArrayList<>();
        this.filteredOffers = new ArrayList<>();
        this.search_text = "";
    }

    public void initialize_visible_offers() throws Exception {
        List<Offer> all_offers = app.getOffers();
        this.all_offers_visible = new ArrayList<>();
        for (Offer offer : all_offers) {
            if (!offer.getMail().equals(app.getCurrentUser().getEmail())) {
                Account account = app.getAccountDAO().get(offer.getMail());
                if(!account.isSleeping()){
                    this.all_offers_visible.add(offer);
                }
            }
        }
    }

    public List<Offer> getAllOffersVisible() throws Exception {
        resetOffers();
        return all_offers_visible;
    }

    public void resetOffers() throws Exception {
        initialize_visible_offers();
        this.researchedOffers = this.all_offers_visible;
        this.filteredOffers = this.all_offers_visible;
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


    /**
     * Search the string in the offers
     * @param motRecherche String to search
     * @return List of the offers which contains the string
     */
    public void searchOffer(String motRecherche) throws Exception {
        if (motRecherche == null || motRecherche.equals("")){
            this.resetOffers();
            return;
        }
        this.search_text = motRecherche;
    }

    public void doResearch() throws Exception {
        this.resetOffers();
        List<Offer> result = new ArrayList<>();
        for (Offer offer : this.researchedOffers){
            String info = "";
            Content ctn = app.getContentDAO().get(offer.getIdContent());
            User user = (User)app.getAccountDAO().get(offer.getMail());
            info += ctn.getTitle() + " " + ctn.getCategory() + " " + ctn.getLocalisation() + " " + ctn.getCategory() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail();
            if (info.toUpperCase().contains(this.search_text.toUpperCase())){
                result.add(offer);
            }
        }
        this.researchedOffers = result;
        this.filteredOffers = result;
    }

    public void filterOffersByDate(String date) throws SQLException {
        Date debut = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        Date fin;
        switch (date) {
            case "Aujourd'hui":
                cal.setTime(debut);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE,59);
                cal.set(Calendar.SECOND,59);
                fin = debut;
                break;
            case "Demain":
                cal.setTime(debut);
                cal.add(Calendar.DATE, 1);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE,59);
                cal.set(Calendar.SECOND,59);
                fin = cal.getTime();
                break;
            case "Cette semaine":
                cal.setTime(debut);
                cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE,59);
                cal.set(Calendar.SECOND,59);
                fin = cal.getTime();
                break;
            case "Ce mois-ci":
                cal.setTime(debut);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE,59);
                cal.set(Calendar.SECOND,59);
                fin = cal.getTime();
                break;
            case "Cette année":
                cal.setTime(debut);
                cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE,59);
                cal.set(Calendar.SECOND,59);
                fin = cal.getTime();
                break;
            default:
                cal.setTime(debut);
                cal.add(Calendar.DATE, 200000);
                fin = cal.getTime();
                break;
        }
        List<Offer> result = new ArrayList<>();
        for(Offer o: this.filteredOffers){
            Content content = app.getContentDAO().get(o.getIdContent());
            boolean isEquipment = content.isEquipment();
            Slot slot = app.getSlotDAO().get(o.getIdOffer()).get(0);
            if (slot != null){
                Date slot_debut = slot.getStartTime();
                Date slot_fin = slot.getEndTime();
                if (isEquipment){
                    if (slot_debut == null){
                        result.add(o);
                    }else if (slot_debut.after(debut) && slot_debut.before(fin)){
                        result.add(o);
                    }
                }else {
                    if (slot_debut.after(debut) && slot_debut.before(fin)) {
                        result.add(o);
                    } else if (slot_fin.after(debut) && slot_fin.before(fin)) {
                        result.add(o);
                    } else if (slot_debut.before(debut) && slot_fin.after(fin)) {
                        result.add(o);
                    }
                }
            }
        }
        this.filteredOffers = result;
    }

    public void filterOffersByPrice(String price) throws Exception {
        double price_value = switch (price) {
            case "Moins de 5€" -> 5.0;
            case "Moins de 10€" -> 10.0;
            case "Moins de 20€" -> 20.0;
            case "Moins de 50€" -> 50.0;
            case "Moins de 100€" -> 100.0;
            case "Tout" -> 999999.99;
            default -> 0.0;
        };
        List<Offer> result = new ArrayList<>();
        for(Offer o: this.filteredOffers){
            Content c = app.getContentDAO().get(o.getIdContent());
            if(c.getPrice() <= price_value){
                result.add(o);
            }
        }
        this.filteredOffers= result;
    }


    public void filterOffersByCategory(String text) throws SQLException {
        List<Offer> result = new ArrayList<>();
        for(Offer o: this.filteredOffers){
            Content c = app.getContentDAO().get(o.getIdContent());
            if(text.contains(c.getCategory())){
                result.add(o);
            }
        }
        this.filteredOffers= result;
    }

    public void filterOffersByLocation(String text){
        return;
    }

    public void filterOffersByEvaluation(String text){
        List<Offer> result = new ArrayList<>();
        EvaluationManager evaluationManager = new EvaluationManager();
        double note_voulu = switch (text){
            case "Tout" -> 0.0;
            case "Plus de 1 étoile" -> 1.0;
            case "Plus de 2 étoiles" -> 2.0;
            case "Plus de 3 étoiles" -> 3.0;
            case "Plus de 4 étoiles" -> 4.0;
            default -> throw new IllegalStateException("Unexpected value: " + text);
        };
        for(Offer o: this.filteredOffers){
            try {
                if(evaluationManager.getAverage(o.getMail()) >= note_voulu){
                    result.add(o);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        this.filteredOffers = result;
    }
}
