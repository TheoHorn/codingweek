package eu.telecomnancy.directdealing.views.home;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

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


    @FXML
    public void applyFilter() throws Exception {
        this.app.notifyObservers();
    }

    @Override
    public void update() throws Exception {
        this.app.getResearchFilterManager().resetFilter();
        for (MenuItem item : this.filter_date.getItems()){
            CheckMenuItem checkItem = (CheckMenuItem) item;
            if (checkItem.isSelected()){
            //this.app.filterOffers(this.app.getSearchBar().searchOffer(this.app.getOffers(), new Date(checkItem.getText())));
            }
        }
        for (MenuItem item : this.filter_price.getItems()){
            CheckMenuItem checkItem = (CheckMenuItem) item;
            if (checkItem.isSelected()){
              this.app.getResearchFilterManager().filterOffersByPrice(checkItem.getText());
            }
        }
        for (MenuItem item : this.filter_evaluation.getItems()){
            CheckMenuItem checkItem = (CheckMenuItem) item;
            if (checkItem.isSelected()){
             //this.app.setOffers(this.app.getSearchBar().searchOffer(this.app.getOffers(), checkItem.getText()));
            }
        }
        for (MenuItem item : this.filter_location.getItems()){
            CheckMenuItem checkItem = (CheckMenuItem) item;
            if (checkItem.isSelected()){
                //this.app.setOffers(this.app.getSearchBar().searchOffer(this.app.getOffers(), checkItem.getText()));
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
