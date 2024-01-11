package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.database.ContentDAO;
import eu.telecomnancy.directdealing.database.DatabaseAccess;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.content.Content;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import static eu.telecomnancy.directdealing.database.ReallyStrongSecuredPassword.generateStrongPasswordHash;
/**
 * MainLauncher class
 */
public class MainLauncher {
    /**
     * Main method
     * @param args arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Main.main(args);
    }
}
