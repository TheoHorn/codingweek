package eu.telecomnancy.directdealing.views.home;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HomeViewControllerAfterResearch extends HomeViewController{

    @FXML
    private MenuButton filter_location;

    @FXML
    private MenuButton filter_date;

    @FXML
    private MenuButton filter_price;

    @FXML
    private MenuButton filter_evaluation;

    @FXML
    private MenuButton filter_category;

    public HomeViewControllerAfterResearch(){
        super();
        this.app.addObserver(this);
    }


    public void handleRadioMenuButton(){
        ToggleGroup group = new ToggleGroup();
        for (MenuItem item : this.filter_evaluation.getItems()){
            RadioMenuItem checkItem = (RadioMenuItem) item;
            checkItem.setToggleGroup(group);
        }
        ToggleGroup group2 = new ToggleGroup();
        for (MenuItem item : this.filter_location.getItems()){
            RadioMenuItem checkItem = (RadioMenuItem) item;
            checkItem.setToggleGroup(group2);
        }
        ToggleGroup group3 = new ToggleGroup();
        for (MenuItem item : this.filter_price.getItems()){
            RadioMenuItem checkItem = (RadioMenuItem) item;
            checkItem.setToggleGroup(group3);
        }
    }


    @FXML
    public void applyFilter() throws Exception {
        this.app.notifyObservers();
    }

    @Override
    public void update() throws Exception {
        this.handleRadioMenuButton();
        this.app.getResearchFilterManager().resetFilter();
        for (MenuItem item : this.filter_date.getItems()){
            CheckMenuItem checkItem = (CheckMenuItem) item;
            //TODO
        }
        for (MenuItem item : this.filter_price.getItems()){
            RadioMenuItem checkItem = (RadioMenuItem) item;
            if (checkItem.isSelected()){
              this.app.getResearchFilterManager().filterOffersByPrice(checkItem.getText());
            }
        }
        for (MenuItem item : this.filter_evaluation.getItems()){
            RadioMenuItem checkItem = (RadioMenuItem) item;
            if (checkItem.isSelected()){
                this.app.getResearchFilterManager().filterOffersByEvaluation(checkItem.getText());
            }
        }
        for (MenuItem item : this.filter_location.getItems()){
            RadioMenuItem checkItem = (RadioMenuItem) item;
            if (checkItem.isSelected()){
                this.app.getResearchFilterManager().filterOffersByLocation(checkItem.getText());
            }
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
        offersListView.getItems().clear();
        this.app.getResearchFilterManager().getFilteredOffers().forEach(offer -> offersListView.getItems().add(offer));
        offersListView.setCellFactory(lv -> new OfferCell());
    }


}
