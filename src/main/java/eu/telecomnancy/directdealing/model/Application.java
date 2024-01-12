package eu.telecomnancy.directdealing.model;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.*;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.AccountManager;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.content.ContentManager;
import eu.telecomnancy.directdealing.model.content.Equipment;
import eu.telecomnancy.directdealing.model.content.Service;
import eu.telecomnancy.directdealing.model.dispute.Dispute;
import eu.telecomnancy.directdealing.model.dispute.DisputeManager;
import eu.telecomnancy.directdealing.model.demande.Demande;
import eu.telecomnancy.directdealing.model.demande.DemandeManager;
import eu.telecomnancy.directdealing.model.evaluation.Evaluation;
import eu.telecomnancy.directdealing.model.evaluation.EvaluationManager;
import eu.telecomnancy.directdealing.model.messaging.MessagingManager;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.OfferManager;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.model.offer.Request;
import eu.telecomnancy.directdealing.model.reservation.ReservationManager;
import eu.telecomnancy.directdealing.model.slot.SlotManager;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;
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
    private final AccountDAO accountDAO;
    /**
     * content DAO
     */
    private final ContentDAO contentDAO;
    /**
     * offer DAO
     */
    private final OfferDAO offerDAO;
    /**
     * slot DAO
     */
    private final SlotDAO slotDAO;
    /**
     * reservation DAO
     */
    private final ReservationDAO reservationDAO;
    /**
     * account manager
     */
    private final AccountManager accountManager;


    /**
     * offer manager
     */
    private final OfferManager offerManager;


    /**
     * content manager
     */
    private final ContentManager contentManager;


    /**
     * dispute manager
     */
    private final DisputeManager disputeManager;


    /**
     * reservation manager
     */
    private final ReservationManager reservationManager;


    /**
     * slot manager
     */
    private final SlotManager slotManager;

    /**
     * the last offer used
     */
    private Offer lastOffer;

    private Account lastAccount;

    /**
     * the research manager
     */
    private final ResearchFilterManager researchFilterManager;
    /**
     * demande DAO
     */
    private DemandeDAO demandeDAO;
    /**
     * demande manager
     */
    private final DemandeManager demandeManager;
    /**
     * evaluation DAO
     */
    private final EvaluationDAO evaluationDAO;
    /**
     * evaluation manager
     */
    private final EvaluationManager evaluationManager;

    /**
     * messaging DAO
     */
    private final MessagingDAO messagingDAO;

    /**
     * messaging manager
     */
    private final MessagingManager messagingManager;

    /**
     * the last demand used
     */
    private Demande lastDemand;
    /**
     * dispute DAO
     */
    private final DisputeDAO disputeDAO;

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
        this.contentManager = new ContentManager();
        this.offerDAO = new OfferDAO();
        this.offerManager = new OfferManager();
        this.demandeDAO = new DemandeDAO();
        this.slotDAO = new SlotDAO();
        this.slotManager = new SlotManager();
        this.reservationDAO = new ReservationDAO();
        this.reservationManager = new ReservationManager();
        this.accountManager = new AccountManager();
        this.researchFilterManager = new ResearchFilterManager();
        this.demandeDAO = new DemandeDAO();
        this.demandeManager = new DemandeManager();
        this.evaluationDAO = new EvaluationDAO();
        this.evaluationManager = new EvaluationManager();
        this.messagingDAO = new MessagingDAO();
        this.messagingManager = new MessagingManager();
        this.disputeDAO = new DisputeDAO();
        this.disputeManager = new DisputeManager();
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

    public List<User> getUsers() throws Exception {
        return this.accountDAO.getUsers();
    }

    public List<Dispute> getDisputes() {
        List<Dispute> disputes = this.disputeDAO.get();
        return disputes;
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

    public OfferManager getOfferManager() {
        return offerManager;
    }

    public ContentManager getContentManager() {
        return contentManager;
    }

    public DisputeManager getDisputeManager() {
        return disputeManager;
    }

    public ReservationManager getReservationManager() {
        return reservationManager;
    }

    public SlotManager getSlotManager() {
        return slotManager;
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

    public DisputeDAO getDisputeDAO() {
        return disputeDAO;
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    /**
     * login the user
     * @param mail Email of the user
     * @param password Password of the user
     * @throws Exception if the login is not correct
     */
    public void login(String mail, String password) throws Exception {
        setCurrentUser(accountManager.login(mail, password));
        if (getCurrentUser() != null) {
            if (getCurrentUser() instanceof User){
                sceneController.switchToHome();
            }
            else {
                sceneController.switchToAdminHome();
            }
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
     * @throws Exception if the sign in is not correct
     */
    public void signin(String mail, String password, String firstname, String lastname, String password_confirm, String city, String address) throws Exception {
        if (!mail.isEmpty() && !password.isEmpty() && !lastname.isEmpty() && !firstname.isEmpty() && !password_confirm.isEmpty()){
            if (!accountManager.isSave(mail)){
                if (!password.equals(password_confirm)){
                    throw new Exception("Les mots de passe sont différents");
                }
                User user = new User(lastname,firstname,mail,500.0, false,generateStrongPasswordHash(password), city, address);
                accountDAO.save(user);
                setCurrentUser(user);
                sceneController.switchToHome();
            }
            else {
                throw new Exception("Email déjà utilisé");
            }
        } else {
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
     * @throws SQLException if the offer is not validate
     */
    public void validateNewOffer(String title, String description, String category, boolean isRequest, double price, File image, List<Slot> slots) throws Exception {
        if (title.isEmpty() || description.isEmpty() || price == 0 || slots.isEmpty() || category == null) {
            throw new Exception("Veuillez remplir tous les champs");
        } else {
            Service service = new Service(title, category, description, image, price, currentUser.getLocalisation());
            int idOffer;
            if (isRequest) {
                if (app.getCurrentUser().getBalance() < price) {
                    throw new Exception("Vous n'avez pas assez de florains");
                }
                app.getCurrentUser().setBalance(app.getCurrentUser().getBalance() - service.getPrice());
                Request request = new Request(Application.getInstance().getCurrentUser().getEmail(), true, service.getIdContent());
                idOffer = getOfferDAO().save(request);
                getAccountDAO().save(getCurrentUser());
            } else {
                Proposal proposal = new Proposal(Application.getInstance().getCurrentUser().getEmail(), false, service.getIdContent());
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
     * @throws SQLException if the offer is not validate
     */
    public void validateNewOffer(String title, String description, String category, boolean isRequest, double price, File image, LocalDate returnDate) throws Exception {
        if (title.isEmpty() || description.isEmpty() || price == 0 || category == null) {
            throw new Exception("Veuillez remplir tous les champs");
        } else {
            Equipment service = new Equipment(title, category, description, image, price, currentUser.getLocalisation());
            int idOffer;
            if (isRequest) {
                if (app.getCurrentUser().getBalance() < price) {
                    throw new Exception("Vous n'avez pas assez de florains");
                }
                Request request = new Request(Application.getInstance().getCurrentUser().getEmail(), true, service.getIdContent());
                idOffer = getOfferDAO().save(request);
                getCurrentUser().setBalance(getCurrentUser().getBalance() - service.getPrice());
                getAccountDAO().save(getCurrentUser());
            } else {
                Proposal proposal = new Proposal(Application.getInstance().getCurrentUser().getEmail(), false, service.getIdContent());
                idOffer = getOfferDAO().save(proposal);
            }
            LocalDateTime tmp = returnDate != null ? returnDate.atStartOfDay() : null;
            Date returnDateDate = tmp != null ? Date.from(tmp.atZone(ZoneId.systemDefault()).toInstant()) : null;
            getSlotDAO().save(new Slot(0, returnDateDate, null, 0, idOffer));
            this.sceneController.switchToHome();
        }
    }

    /**
     * update the current account with the new information
     *
     * @param name the new name
     * @param surname the new surname
     * @param city  the new city
     * @param address the new address
     * @return true if the account is updated, false otherwise
     * @throws Exception if the account is not updated
     */
    public boolean updateCurrentAccount(String name, String surname, String city, String address) throws Exception {
        if (!(name.isEmpty() || surname.isEmpty() || city.isEmpty() || address.isEmpty())) {
            this.getCurrentUser().setFirstName(name);
            this.getCurrentUser().setLastName(surname);
            this.getCurrentUser().setCity(city);
            this.getCurrentUser().setAddress(address);
            accountDAO.save(this.getCurrentUser());
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
        researchFilterManager.searchOffer(motRecherche);
    }

    public ResearchFilterManager getResearchFilterManager() {
        return researchFilterManager;
    }

    public boolean updateCurrentUserSleeping(boolean isSleeping) throws Exception {
        boolean b = accountManager.updateSleeping(this.getCurrentUser(), isSleeping);
        notifyObservers();
        return b;
    }

    public Account getLastAccount() {
        return lastAccount;
    }

    public void sendEvaluation(String mailEvaluator, String mailEvaluated, String note) throws Exception {
        int noteInt = evaluationManager.convert(note);
        Evaluation evaluation = new Evaluation(mailEvaluator, mailEvaluated, noteInt);
        evaluationDAO.save(evaluation);
        notifyObservers();
    }

    public void setLastAccount(Account lastAccount) {
        this.lastAccount = lastAccount;
    }

    public MessagingManager getMessagingManager() {
        return messagingManager;
    }

    public MessagingDAO getMessagingDAO() {
        return messagingDAO;
    }

    public void deleteUser(Account account) throws Exception {
        if (account.equals(this.getCurrentUser())){
            // The admin delete himself
            this.deleteCurrentUser();
            this.sceneController.switchToLoginView();
        }
        this.accountManager.delete(account);
        this.notifyObservers();
    }

    public void deleteDispute(Dispute dispute) throws Exception {
        app.getDisputeDAO().delete(dispute.getIdDispute());
        this.notifyObservers();
    }

    public void deleteOffer(Offer offer) throws Exception {
        this.offerManager.delete(offer);
        this.notifyObservers();
    }

    public void validateNewDemand(Offer offer, List<Slot> newSlots) throws Exception {
        if (offer == null) {
            throw new Exception("Veuillez remplir tous les champs");
        } else {
            int idSlot;
            for (Slot newSlot : newSlots) {
                idSlot = this.getSlotDAO().save(newSlot);
                this.demandeDAO.save(new Demande(idSlot, currentUser.getEmail(), new Date(), 0));
                int idOffer = this.slotDAO.get(idSlot,false).getIdOffer();
                Offer temp = this.offerDAO.get(idOffer);
                if (!temp.isRequest()){
                    Content content = this.contentDAO.get(this.offerDAO.get(idOffer).getIdContent());
                    currentUser.setBalance(currentUser.getBalance() - content.getPrice());
                    getAccountDAO().save(currentUser);
                }
            }
            this.sceneController.switchToHome();
        }
    }

    public void setLastDemand(Demande lastDemand) {
        this.lastDemand = lastDemand;
    }

    public Demande getLastDemand() {
        return lastDemand;
    }

    public void saveDemandeStatus(String string) throws Exception {
        switch (string) {
            case "En attente":
                this.lastDemand.setStatus(0);
                break;
            case "Accepter":
                this.lastDemand.setStatus(1);
                // Change demand into reservation
                Reservation reservation = this.reservationManager.getFromDemand(this.lastDemand);
                this.reservationDAO.save(reservation);
                // add credit to the owner of the offer
                int idSlot = this.lastDemand.getIdSlot();
                int idOffer = this.slotDAO.get(idSlot,false).getIdOffer();
                Offer offer = this.offerDAO.get(idOffer);
                Content content = this.contentDAO.get(this.offerDAO.get(idOffer).getIdContent());
                if (offer.isRequest()){
                    Account answer = this.accountDAO.get(lastDemand.getMail());
                    answer.setBalance(answer.getBalance() + content.getPrice());
                    getAccountDAO().save(answer);
                } else {
                    currentUser.setBalance(currentUser.getBalance() + content.getPrice());
                    getAccountDAO().save(currentUser);
                }
                break;
            case "Refuser":
                this.lastDemand.setStatus(2);
                break;
        }
        getDemandeDAO().save(this.lastDemand);
        notifyObservers();
    }

    public void deleteDemande() {
        try {
            getDemandeDAO().delete(this.lastDemand.getIdDemande());
            int idSlot = this.lastDemand.getIdSlot();
            int idOffer = this.slotDAO.get(idSlot,false).getIdOffer();
            Content content = this.contentDAO.get(this.offerDAO.get(idOffer).getIdContent());
            Account demander = this.accountDAO.get(this.lastDemand.getMail());
            demander.setBalance(demander.getBalance() + content.getPrice());
            getAccountDAO().save(demander);
            notifyObservers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNewReclamation(String attacker, String defender, String content) {
        Dispute dispute = new Dispute(getLastDemand().getIdDemande(), content, attacker, defender);
        getDisputeDAO().save(dispute);
    }
}
