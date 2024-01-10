package eu.telecomnancy.directdealing.views.home;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.Date;

public class HomeViewControllerAfterResearch extends HomeViewController{

    @FXML
    private MenuButton filter_location;

    @FXML
    private MenuButton filter_date;

    @FXML
    private MenuButton filter_price;

    @FXML
    private MenuButton filter_evaluation;

    public HomeViewControllerAfterResearch(){
        super();
        this.app.addObserver(this);
    }

    @FXML
    public void intialize(){
        /*
        ArrayList<String> dates = this.app.getFilterDate();
        ArrayList<String> prices = this.app.getFilterPrice();
        ArrayList<String> evaluations = this.app.getFilterEvaluation();
        ArrayList<String> locations = this.app.getFilterLocation();
        for(String date : dates){
            CheckMenuItem item = new CheckMenuItem(date);
            this.filter_date.getItems().add(item);
        }
        for(String price : prices){
            CheckMenuItem item = new CheckMenuItem(price);
            this.filter_price.getItems().add(item);
        }
        for(String evaluation : evaluations){
            CheckMenuItem item = new CheckMenuItem(evaluation);
            this.filter_evaluation.getItems().add(item);
        }
        for(String location : locations){
            CheckMenuItem item = new CheckMenuItem(location);
            this.filter_location.getItems().add(item);
        }
        */

    }

    @Override
    public void update() throws Exception {
       for (MenuItem item : this.filter_date.getItems()){
              CheckMenuItem checkItem = (CheckMenuItem) item;
              if (checkItem.isSelected()){
                //this.app.setOffers(this.app.getSearchBar().searchOffer(this.app.getOffers(), new Date(checkItem.getText())));
              }
         }
          for (MenuItem item : this.filter_price.getItems()){
                CheckMenuItem checkItem = (CheckMenuItem) item;
                if (checkItem.isSelected()){
                 //this.app.setOffers(this.app.getSearchBar().searchOffer(this.app.getOffers(), checkItem.getText()));
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
        offersListView.getItems().clear();
        this.app.getOffers().forEach(offer -> offersListView.getItems().add(offer));
        offersListView.setCellFactory(lv -> new OfferCell());
    }


}
