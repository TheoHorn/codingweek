package eu.telecomnancy.directdealing.database;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * ReallyStrongSecuredPassword is the class that allows to secure the password of the user
 * from https://github.com/lokeshgupta1981/Core-Java/tree/master/src/main/java/com/howtodoinjava/hashing/password/demo
 */
public class ReallyStrongSecuredPassword {

    /**
     * validatePassword method allows to check if the password is correct
     * @param originalPassword the password to check
     * @param storedPassword the password stored in the database
     * @return true if the password is correct, false if not
     * @throws NoSuchAlgorithmException if the algorithm is not found
     * @throws InvalidKeySpecException if the key is not valid
     */
    public static boolean validatePassword(String originalPassword, String storedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt,
                iterations, hash.length * 8);

        SecretKeyFactory skf =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;

        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }

        return diff == 0;
    }

    /**
     * generateStrongPasswordHash method allows to generate a strong password
     * @param password the password to hash
     * @return the hashed password
     * @throws NoSuchAlgorithmException if the algorithm is not found
     * @throws InvalidKeySpecException if the key is not valid
     */
    public static String generateStrongPasswordHash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt().getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);

    }

    /**
     * getSalt method allows to get the salt
     * @return the salt
     * @throws NoSuchAlgorithmException
     */
    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }

    /**
     * toHex method allows to convert a byte array to a hex string
     * @param array the byte array to convert
     * @return the hex string
     * @throws NoSuchAlgorithmException if the algorithm is not found
     */
    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    /**
     * fromHex method allows to convert a hex string to a byte array
     * @param hex the hex string to convert
     * @return the byte array
     * @throws NoSuchAlgorithmException if the algorithm is not found
     */
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),
                    16);
        }
        return bytes;
    }
}