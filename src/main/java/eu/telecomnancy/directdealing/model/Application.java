package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.model.offer.Request;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static volatile Application instance = null;
    private Account currentUser;
    private final List<Offer> offers;
    private final List<Observer> observers;
    private SceneController sceneController;
    private AccountManager accountManager;
    private Application() {
        this.currentUser = null;
        this.offers = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.accountManager = new AccountManager();
//        test
        this.offers.add(new Proposal(null, null, null));
        this.offers.add(new Request(null, null));
//        test
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

    public SceneController getSceneController() {
        return sceneController;
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public boolean login(String mail, String password) throws Exception {
        setCurrentUser(AccountManager.login(mail, password));
        if (getCurrentUser() != null) {
            sceneController.switchToHome();
            notifyObservers();
            return true;
        }
        System.out.println("Login failed");
        return false;
    }

    public boolean signin(String mail, String password, String firstname, String lastname, String password_confirm) throws Exception {
        if (!mail.isEmpty() && !password.isEmpty() && !lastname.isEmpty() && !firstname.isEmpty() && !password_confirm.isEmpty()){
            System.out.println(!accountManager.isSave(mail));
            if (!accountManager.isSave(mail)){
                User user = new User(lastname,firstname,mail,500.0, false,password);
                accountManager.addUser(user);
                setCurrentUser(user);
                sceneController.switchToHome();
                System.out.println("[Debug:AccountCreatingController] Succesfull");
                return true;
            }
            else {
                System.out.println("[Debug:AccountCreatingController] Email déjà utilisé");
                return false;
            }
        } else {
            System.out.println("[Debug:AccountCreatingController] Veuillez remplir tous les champs");
            return false;
        }
    }
}
