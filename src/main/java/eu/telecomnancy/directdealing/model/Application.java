package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.*;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Service;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.model.offer.Request;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;

public class Application {
    public static volatile Application instance = null;
    private Account currentUser;
    private final List<Offer> offers;
    private final List<Observer> observers;
    private SceneController sceneController;
    private AccountManager accountManager;
    private ContentManager contentManager;
    private OfferManager offerManager;

    private SlotManager slotManager;
    private ReservationManager reservationManager;

    private Application() {
        this.currentUser = null;
        this.offers = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.accountManager = new AccountManager();
        this.contentManager = new ContentManager();
        this.offerManager = new OfferManager();
        this.slotManager = new SlotManager();
        this.reservationManager = new ReservationManager();

//        test
        this.offers.add(new Proposal(null, null, null, false));
        this.offers.add(new Request(null, null, null, false));
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

    public void deleteCurrentUser() {
        this.currentUser = null;
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

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public ContentManager getContentManager() {
        return contentManager;
    }

    public OfferManager getOfferManager() {
        return offerManager;
    }

    public SlotManager getSlotManager() {
        return slotManager;
    }

    public ReservationManager getReservationManager() {
        return reservationManager;
    }

    public boolean login(String mail, String password) throws Exception {
        setCurrentUser(accountManager.login(mail, password));
        if (getCurrentUser() != null) {
            sceneController.switchToHome();
            notifyObservers();
            return true;
        }
        System.out.println("Login failed");
        return false;
    }

    public int signin(String mail, String password, String firstname, String lastname, String password_confirm) throws Exception {
        if (!mail.isEmpty() && !password.isEmpty() && !lastname.isEmpty() && !firstname.isEmpty() && !password_confirm.isEmpty()){
            System.out.println(!accountManager.isSave(mail));
            if (!accountManager.isSave(mail)){
                User user = new User(lastname,firstname,mail,500.0, false,password);
                accountManager.addUser(user);
                setCurrentUser(user);
                sceneController.switchToHome();
                System.out.println("[Debug:AccountCreatingController] Succesfull");
                return 0;
            }
            else {
                System.out.println("[Debug:AccountCreatingController] Email déjà utilisé");
                return 1;
            }
        } else {
            System.out.println("[Debug:AccountCreatingController] Veuillez remplir tous les champs");
            return 2;
        }
    }

    public boolean validateNewOffer(String title, String description, String category, LocalDate startDate, LocalDate endDate, boolean isRequest, double price) throws SQLException {
        LocalDateTime startOfDay = startDate.atStartOfDay();
        Date startDateCommit = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        startOfDay = endDate.atStartOfDay();
        Date endDateCommit = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        if (title.isEmpty() || description.isEmpty() || price == 0 || startDate == null || endDate == null){
            System.out.println("Veuillez remplir tous les champs");
            return false;
        } else {
            if (isRequest) {
                Service service = new Service(title, "", description, null, price);
                Request request = new Request((User) Application.getInstance().getCurrentUser(), service, new Slot(startDateCommit, endDateCommit,0), true);
                getOfferManager().addRequest(request);
            } else {
                Service service = new Service(title, "", description, null, price);
                Proposal proposal = new Proposal((User) Application.getInstance().getCurrentUser(), service, new Slot(startDateCommit, endDateCommit,0), false);
                getOfferManager().addProposal(proposal);
            }
            return true;
        }
    }
}
