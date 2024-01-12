package eu.telecomnancy.directdealing.model.account;

import eu.telecomnancy.directdealing.database.DatabaseAccess;
import eu.telecomnancy.directdealing.database.EvaluationDAO;
import eu.telecomnancy.directdealing.model.Reservation;
import eu.telecomnancy.directdealing.model.demande.Demande;
import eu.telecomnancy.directdealing.model.evaluation.Evaluation;
import eu.telecomnancy.directdealing.model.messaging.Messaging;
import eu.telecomnancy.directdealing.model.offer.Offer;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;
import static eu.telecomnancy.directdealing.database.ReallyStrongSecuredPassword.validatePassword;

/**
 * Account Managing class
 */
public class AccountManager {
    /**
     * isSave allow to know if the account is save in the database
     * @param mail Email of the account
     * @return true if the account is save in the database, false otherwise
     * @throws SQLException if the account is not save in the database
     */
    public boolean isSave(String mail) throws SQLException {
        Account account = app.getAccountDAO().get(mail);
        if (account == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * log in the account
     * @param mail Email of the account
     * @param password Password of the account
     * @return the account if the login is correct, null otherwise
     * @throws Exception if the login is not correct
     */
    public Account login(String mail, String password) throws Exception {
        Account account = app.getAccountDAO().get(mail);
        if (account == null) {
            return null;
        } else {
            String passwordFromDatabase = account.getPassword();
            boolean matched = validatePassword(password, passwordFromDatabase);
            if (matched) {
                return account;
            } else {
                return null;
            }
        }
    }

    /**
     * update the password of the account
     * @param old_password Old password of the account
     * @param new_password New password of the account
     * @param account Account to update
     * @return true if the password is updated, false otherwise
     * @throws Exception if the password is not updated
     */
    public boolean updatePasswordAccount(String old_password, String new_password, Account account) throws Exception {
        if (app.getAccountManager().login(account.getEmail(), old_password) != null) {
            account.setPassword(new_password);
            app.getAccountDAO().save(account);
            return true;
        }
        return false;
    }

    public boolean updateSleeping(Account account, boolean isSleeping) throws Exception {
        if (account == null) {
            return false;
        } else {
            account.updateSleeping(isSleeping);
            app.getAccountDAO().save(account);
            return true;
        }
    }

    public void delete(Account account) throws SQLException {

        // Delete all offer related to the account
        List<Offer> offers = app.getOfferDAO().get(account.getEmail());
        offers.forEach((offer) -> {
            try {
                app.getOfferManager().delete(offer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Delete all reservation related to the account
        List<Reservation> reservations = app.getReservationDAO().get(account.getEmail());
        reservations.forEach((reservation) -> {
            try {
                app.getReservationManager().delete(reservation);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Delete all evaluation related to the account
        List<Evaluation> evaluations = app.getEvaluationDAO().get(account.getEmail()); // For the evaluated
        evaluations.forEach((evaluation) -> {
            try {
                app.getEvaluationManager().delete(evaluation);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        evaluations = app.getEvaluationDAO().getEvaluator(account.getEmail()); // For the evaluator
        evaluations.forEach((evaluation) -> {
            try {
                app.getEvaluationManager().delete(evaluation);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        // Delete all message related to the account
        List<Messaging> messagings = app.getMessagingDAO().getSender(account.getEmail()); // For the sender
        messagings.forEach((messaging) -> {
            try {
                app.getMessagingManager().delete(messaging);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        messagings = app.getMessagingDAO().getReceiver(account.getEmail()); // For the receiver
        messagings.forEach((messaging) -> {
            try {
                app.getMessagingManager().delete(messaging);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Delete all demande related to slot
        List<Demande> demandes = app.getDemandeDAO().get(account.getEmail());
        demandes.forEach((demande) -> {
            try {
                app.getDemandeManager().delete(demande);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Delete the account
        app.getAccountDAO().delete(account.getEmail());
    }
}
