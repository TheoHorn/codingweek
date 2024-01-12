package eu.telecomnancy.directdealing.views.home;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * HomeViewController class
 */
public class HomeViewController implements Observer {

    /**
     * ListView of the offers
     */
    @FXML
    protected ListView<Offer> offersListView;

    @FXML
    public TextField filter_city;

    @FXML
    private ChoiceBox filter_date;

    @FXML
    private ChoiceBox filter_price;

    @FXML
    private ChoiceBox filter_evaluation;

    @FXML
    private MenuButton filter_category;

    /**
     * Application instance
     */
    protected Application app;

    /**
     * Constructor of the home view controller
     */
    public HomeViewController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    public HomeViewController(ListView<Offer> offersListView) throws Exception {
        this.offersListView = offersListView;
        this.app = Application.getInstance();
        this.app.getResearchFilterManager().resetOffers();
        this.app.addObserver(this);
    }

    @FXML
    public void applyFilter() throws Exception {
        this.app.notifyObservers();
    }

    /**
     * update method
     */
    @Override
    public void update() throws Exception {
        this.app.getResearchFilterManager().resetOffers();
        this.app.getResearchFilterManager().doResearch();
        if (this.filter_date.getValue() != null){
            this.app.getResearchFilterManager().filterOffersByDate(this.filter_date.getValue().toString());
        }
        if (this.filter_price.getValue() != null){
            this.app.getResearchFilterManager().filterOffersByPrice(this.filter_price.getValue().toString());
        }
        if (this.filter_city.getText() != null){
            this.app.getResearchFilterManager().filterOffersByLocation(this.filter_city.getText());
        }
        if (this.filter_evaluation.getValue() != null){
            this.app.getResearchFilterManager().filterOffersByEvaluation(this.filter_evaluation.getValue().toString());
        }
        StringBuilder category_available = new StringBuilder();
        for (MenuItem item : this.filter_category.getItems()){
            CheckMenuItem checkItem = (CheckMenuItem) item;
            //we can have multiple category that we want to filter
            if (checkItem.isSelected()){
                category_available.append(checkItem.getText());
            }
        }
        if (!category_available.toString().isEmpty()){
            this.app.getResearchFilterManager().filterOffersByCategory(category_available.toString());
        }
        this.filter_city.setText(this.app.getCurrentUser().getCity());
        offersListView.getItems().clear();
        System.out.println("Offers to print: " + this.app.getResearchFilterManager().getFilteredOffers().size());
        this.app.getResearchFilterManager().getFilteredOffers().forEach(offer -> offersListView.getItems().add(offer));
        offersListView.setCellFactory(lv -> new OfferCell());
    }
}
