package eu.telecomnancy.directdealing.model;

import com.dlsc.gemsfx.daterange.DateRange;
import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.*;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.AccountManager;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Equipment;
import eu.telecomnancy.directdealing.model.content.Service;
import eu.telecomnancy.directdealing.model.demande.Demande;
import eu.telecomnancy.directdealing.model.demande.DemandeManager;
import eu.telecomnancy.directdealing.model.evaluation.Evaluation;
import eu.telecomnancy.directdealing.model.evaluation.EvaluationManager;
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

/**
 * Application class singleton
 */
public class Application {
    /**
     * instance of the application
     */
    public static volatile Application instance = null;
    /**
     * current user
     */
    private Account currentUser;
    /**
     * list of the offers
     */
    private List<Offer> offers;
    /**
     * list of the proposals
     */
    private final List<Proposal> myProposals;
    /**
     * list of the observers
     */
    private final List<Observer> observers;
    /**
     * scene controller
     */
    private SceneController sceneController;
    /**
     * account DAO
     */
    private AccountDAO accountDAO;
    /**
     * content DAO
     */
    private ContentDAO contentDAO;
    /**
     * offer DAO
     */
    private OfferDAO offerDAO;
    /**
     * slot DAO
     */
    private SlotDAO slotDAO;
    /**
     * reservation DAO
     */
    private ReservationDAO reservationDAO;
    /**
     * account manager
     */
    private AccountManager accountManager;

    /**
     * the last offer used
     */
    private Offer lastOffer;

    private Account lastAccount;

    /**
     * the research manager
     */
    private ResearchManager researchManager;
    private DemandeDAO demandeDAO;
    private DemandeManager demandeManager;
    private EvaluationDAO evaluationDAO;
    private EvaluationManager evaluationManager;

    /**
     * Constructor of the application that initialize the lists
     */
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
        this.researchManager = new ResearchManager();
        this.demandeDAO = new DemandeDAO();
        this.demandeManager = new DemandeManager();
        this.evaluationDAO = new EvaluationDAO();
        this.evaluationManager = new EvaluationManager();

    }

    /**
     * Get the instance of the application
     * @return instance of the application
     */
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

    public void notifyObservers() throws Exception {
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

    public List<Offer> getOffers() throws Exception {
        offers = this.offerDAO.get();
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

    public DemandeDAO getDemandeDAO() {
        return demandeDAO;
    }

    public DemandeManager getDemandeManager() {
        return demandeManager;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public ReservationDAO getReservationDAO() {
        return reservationDAO;
    }

    public EvaluationDAO getEvaluationDAO() {
        return evaluationDAO;
    }

    public EvaluationManager getEvaluationManager() {
        return evaluationManager;
    }

    public void addOffer(Offer offer) {
        System.out.println("Add offer");
        offers.add(offer);
    }

    /**
     * login the user
     * @param mail Email of the user
     * @param password Password of the user
     * @return true if the login is correct, false otherwise
     * @throws Exception if the login is not correct
     */
    public void login(String mail, String password) throws Exception {
        setCurrentUser(accountManager.login(mail, password));
        if (getCurrentUser() != null) {
            sceneController.switchToHome();
            notifyObservers();
        } else {
            throw new Exception("Mot de passe ou email incorrect");
        }
    }

    /**
     * sign in the user
     * @param mail Email of the user
     * @param password Password of the user
     * @param firstname First name of the user
     * @param lastname Last name of the user
     * @param password_confirm Password confirmation of the user
     * @throws Exception
     */
    public void signin(String mail, String password, String firstname, String lastname, String password_confirm) throws Exception {
        if (!mail.isEmpty() && !password.isEmpty() && !lastname.isEmpty() && !firstname.isEmpty() && !password_confirm.isEmpty()){
            System.out.println(!accountManager.isSave(mail));
            if (!accountManager.isSave(mail)){
                if (!password.equals(password_confirm)){
                    System.out.println("[Debug:AccountCreatingController] Mot de passe non identique");
                    throw new Exception("Les mots de passe sont différents");
                }
                String generateStrongPasswordHash;
                User user = new User(lastname,firstname,mail,500.0, false,generateStrongPasswordHash(password), "Nancy");
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

    /**
     * validate a new offer
     * @param title Title of the offer
     * @param description Description of the offer
     * @param category Category of the offer
     * @param isRequest Boolean to know if the offer is a request or a proposal
     * @param price Price of the offer
     * @param slots slots List of the slots of the offer
     * @return true if the offer is validated, false otherwise
     * @throws SQLException if the offer is not validate
     */
    public void validateNewOffer(String title, String description, String category, boolean isRequest, double price, File image, List<Slot> slots) throws Exception {
        System.out.println("category is:" + category);
        if (title.isEmpty() || description.isEmpty() || price == 0 || slots.isEmpty() || image == null || category == null) {
            System.out.println("Veuillez remplir tous les champs");
            throw new Exception("Veuillez remplir tous les champs");
        } else {
            Service service = new Service(title, category, description, image, price);
            int idOffer;
            if (isRequest) {
                Request request = new Request(((User) Application.getInstance().getCurrentUser()).getEmail(), true, service.getIdContent());
                idOffer = getOfferDAO().save(request);
            } else {
                Proposal proposal = new Proposal(((User) Application.getInstance().getCurrentUser()).getEmail(), false, service.getIdContent());
                idOffer = getOfferDAO().save(proposal);
            }
            for (Slot slot : slots) {
                getSlotDAO().save(new Slot(slot.getId(), slot.getStartTime(), slot.getEndTime(), slot.getRecurrence(), idOffer));
            }
            this.sceneController.switchToHome();
        }
    }

    /**
     * validate a new offer
     * @param title Title of the offer
     * @param description Description of the offer
     * @param category Category of the offer
     * @param isRequest Boolean to know if the offer is a request or a proposal
     * @param price Price of the offer
     * @param returnDate Return date of the offer
     * @return true if the offer is validated, false otherwise
     * @throws SQLException if the offer is not validate
     */
    public void validateNewOffer(String title, String description, String category, boolean isRequest, double price, File image, LocalDate returnDate) throws Exception {
        System.out.println("category is:" + category);
        if (title.isEmpty() || description.isEmpty() || price == 0 || image == null || category == null) {
            System.out.println("Veuillez remplir tous les champs");
            throw new Exception("Veuillez remplir tous les champs");
        } else {
            Equipment service = new Equipment(title, category, description, image, price);
            int idOffer;
            if (isRequest) {
                Request request = new Request(((User) Application.getInstance().getCurrentUser()).getEmail(), true, service.getIdContent());
                idOffer = getOfferDAO().save(request);
            } else {
                Proposal proposal = new Proposal(((User) Application.getInstance().getCurrentUser()).getEmail(), false, service.getIdContent());
                idOffer = getOfferDAO().save(proposal);
            }
            if (returnDate != null) {
                System.out.println(returnDate);
                LocalDateTime tmp = returnDate.atStartOfDay();
                Date returnDateDate = Date.from(tmp.atZone(ZoneId.systemDefault()).toInstant());
                getSlotDAO().save(new Slot(0, returnDateDate, null, 0, idOffer));
            }
            this.sceneController.switchToHome();
        }
    }

    /**
     * update the current account
     * @param name First name
     * @param surname Last name
     * @return true if the account is updated, false otherwise
     * @throws Exception if the account is not updated
     */
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

    /**
     * update the current password
     * @param oldPassword Old password
     * @param newPassword New password
     * @param confirmPassword Password confirmation
     * @return true if the password is updated, false otherwise
     * @throws Exception if the password is not updated
     */
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

    /**
     * create a new database
     * @param file File of the database
     * @throws SQLException if the database is not created
     */
    public void createNewDatabaseFile(File file) throws SQLException {
        DatabaseAccess.createDatabase(file);
        DatabaseAccess.connectToDatabase(file.getAbsolutePath());
    }

    /**
     * open a database from a file
     * @param file File of the database
     * @throws SQLException if the database is not opened
     */
    public void openDatabaseFile(File file) throws SQLException {
        DatabaseAccess.connectToDatabase(file.getAbsolutePath());
    }

    public Offer getLastOffer() {
        if (lastOffer != null){
            return lastOffer;
        }else{
            return offers.get(0);
        }
    }

    public void setLastOffer(Offer item) {
        this.lastOffer = item;
    }

    public void researchOffer(String motRecherche) throws Exception {
        researchManager.searchOffer(motRecherche);
    }

    public ResearchManager getResearchManager() {
        return researchManager;
    }

    public boolean updateCurrentUserSleeping(boolean isSleeping) throws Exception {
        System.out.println(isSleeping);
        boolean b = accountManager.updateSleeping(this.getCurrentUser(), isSleeping);
        notifyObservers();
        return b;
    }

    public void setLastAccount(Account lastAccount) {
        this.lastAccount = lastAccount;
    }

    public Account getLastAccount() {
        return lastAccount;
    }

    public boolean sendEvaluation(String mailEvaluator, String mailEvaluated, String note) throws Exception {
        int noteInt = evaluationManager.convert(note);
        Evaluation evaluation = new Evaluation(mailEvaluator, mailEvaluated, noteInt);
        evaluationDAO.save(evaluation);
        notifyObservers();
        return true;
    }
}
