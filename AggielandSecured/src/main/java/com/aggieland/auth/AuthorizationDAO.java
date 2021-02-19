package com.aggieland.auth;

import com.google.common.hash.Hashing;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class AuthorizationDAO {

    private static final Logger LOG = Logger.getLogger(AuthorizationDAO.class.getName());

    public static String generateSaltedPassword(String enteredPassword) {
        return BCrypt.hashpw(enteredPassword,BCrypt.gensalt(15));
    }

    public static boolean checkPassword(String enteredPassword, String dbPassword) {
        return BCrypt.checkpw(enteredPassword,dbPassword);
    }

    public static String hashEncrypt(String plainText) {
        return Hashing.sha512().hashString(plainText, StandardCharsets.UTF_8).toString();
    }

    public static boolean checkHashEncrypt(String plainText, String cipherText) {
        String userInput = Hashing.sha512().hashString(plainText, StandardCharsets.UTF_8).toString();
        return userInput.equals(cipherText);
    }
}
