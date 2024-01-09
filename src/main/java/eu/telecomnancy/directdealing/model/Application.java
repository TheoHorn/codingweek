package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.offer.Offer;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static volatile Application instance = null;
    private Account currentUser;
    private final List<Offer> offers;
    private final List<Observer> observers;

    private Application() {
        this.currentUser = null;
        this.offers = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public static Application getInstance() {
        Application result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Application.class) {
            if (instance == null) {
                instance = new Application();
            }
            return instance;
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeAllObservers() {
        this.observers.clear();
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
