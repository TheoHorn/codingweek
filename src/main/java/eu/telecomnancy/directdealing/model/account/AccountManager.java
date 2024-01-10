package eu.telecomnancy.directdealing.model.account;

import eu.telecomnancy.directdealing.database.DatabaseAccess;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;
import static eu.telecomnancy.directdealing.database.ReallyStrongSecuredPassword.validatePassword;

public class AccountManager {
    public boolean isSave(String mail) throws SQLException {
        Account account = app.getAccountDAO().get(mail);
        if (account == null) {
            return false;
        } else {
            return true;
        }
    }
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

    public boolean updatePasswordAccount(String old_password, String new_password, Account account) throws Exception {
        if (app.getAccountManager().login(account.getEmail(), old_password) != null) {
            account.setPassword(new_password);
            app.getAccountDAO().save(account);
            return true;
        }
        return false;
    }
}
