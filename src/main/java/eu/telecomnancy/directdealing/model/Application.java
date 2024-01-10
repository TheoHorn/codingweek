package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.*;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.AccountManager;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Service;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.model.offer.Request;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static eu.telecomnancy.directdealing.database.ReallyStrongSecuredPassword.generateStrongPasswordHash;

public class Application {
    public static volatile Application instance = null;
    private Account currentUser;
    private final List<Offer> offers;
    private final List<Proposal> myProposals;
    private final List<Observer> observers;
    private SceneController sceneController;
    private AccountDAO accountDAO;
    private ContentDAO contentDAO;
    private OfferDAO offerDAO;
    private SlotDAO slotDAO;
    private ReservationDAO reservationDAO;
    private AccountManager accountManager;

    private Application() {
        this.currentUser = null;
        this.offers = new ArrayList<>();
        this.myProposals = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.accountDAO = new AccountDAO();
        this.contentDAO = new ContentDAO();
        this.offerDAO = new OfferDAO();
        this.slotDAO = new SlotDAO();
        this.reservationDAO = new ReservationDAO();
        this.accountManager = new AccountManager();

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

    public List<Proposal> getMyProposals(){
        return myProposals;
    }

    public SceneController getSceneController() {
        return sceneController;
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public ContentDAO getContentDAO() {
        return contentDAO;
    }

    public OfferDAO getOfferDAO() {
        return offerDAO;
    }

    public SlotDAO getSlotDAO() {
        return slotDAO;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public ReservationDAO getReservationDAO() {
        return reservationDAO;
    }

    public boolean login(String mail, String password) throws Exception {
        setCurrentUser(accountManager.login(mail, password));
        if (getCurrentUser() != null) {
            sceneController.switchToHome();
            notifyObservers();
        } else {
            throw new Exception("Mot de passe ou email incorrect");
        }
        System.out.println("Login failed");
        return false;
    }

    public void signin(String mail, String password, String firstname, String lastname, String password_confirm) throws Exception {
        if (DatabaseAccess.connection == null) {
            throw new Exception("Veuillez créer ou ouvrir une base de données (Fichier)");
        }
        if (!mail.isEmpty() && !password.isEmpty() && !lastname.isEmpty() && !firstname.isEmpty() && !password_confirm.isEmpty()){
            System.out.println(!accountManager.isSave(mail));
            if (!accountManager.isSave(mail)){
                if (!password.equals(password_confirm)){
                    System.out.println("[Debug:AccountCreatingController] Mot de passe non identique");
                    throw new Exception("Les mots de passe sont différents");
                }
                String generateStrongPasswordHash;
                User user = new User(lastname,firstname,mail,500.0, false,generateStrongPasswordHash(password));
                accountDAO.save(user);
                setCurrentUser(user);
                sceneController.switchToHome();
                System.out.println("[Debug:AccountCreatingController] Succesfull");
            }
            else {
                System.out.println("[Debug:AccountCreatingController] Email déjà utilisé");
                throw new Exception("Email déjà utilisé");
            }
        } else {
            System.out.println("[Debug:AccountCreatingController] Veuillez remplir tous les champs");
            throw new Exception("Veuillez remplir tous les champs");
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
                getOfferDAO().save(request);
            } else {
                Service service = new Service(title, "", description, null, price);
                Proposal proposal = new Proposal((User) Application.getInstance().getCurrentUser(), service, new Slot(startDateCommit, endDateCommit,0), false);
                getOfferDAO().save(proposal);
            }
            return true;
        }
    }

    public boolean updateCurrentAccount(String name, String surname) throws Exception {
        if (!(name.isEmpty() || surname.isEmpty())) {
            this.getCurrentUser().setFirstName(name);
            this.getCurrentUser().setLastName(surname);
            accountDAO.save(this.getCurrentUser());;
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean updateCurrentPassword(String oldPassword, String newPassword, String confirmPassword) throws Exception {
        boolean isGood = false;
        if (newPassword.equals(confirmPassword)) {
            isGood = accountManager.updatePasswordAccount(oldPassword, newPassword, this.getCurrentUser());
        }
        if (isGood) {
           sceneController.switchToHome();
        }
        return isGood;
    }

    public void createNewDatabaseFile(File file) throws SQLException {
        DatabaseAccess.createDatabase(file);
        DatabaseAccess.connectToDatabase(file.getAbsolutePath());
    }

    public void openDatabaseFile(File file) throws SQLException {
        DatabaseAccess.connectToDatabase(file.getAbsolutePath());
    }
}
