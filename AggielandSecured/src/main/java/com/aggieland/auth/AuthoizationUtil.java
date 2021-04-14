package com.aggieland.auth;

import com.google.common.hash.Hashing;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class AuthoizationUtil {

    private static final Logger LOG = Logger.getLogger(AuthoizationUtil.class.getName());

    public static String hashSaltedPassword(String saltedPassword, String salt) {
        return BCrypt.hashpw(saltedPassword,salt);
    }

    public static boolean checkPassword(String saltedPassword, String dbPassword) {
        return BCrypt.checkpw(saltedPassword,dbPassword);
    }

    public static String generateSalt() {
        return BCrypt.gensalt(5);
    }







    public static String hashEncrypt(String plainText) {
        return Hashing.sha512().hashString(plainText, StandardCharsets.UTF_8).toString();
    }

    public static boolean checkHashEncrypt(String plainText, String cipherText) {
        String userInput = Hashing.sha512().hashString(plainText, StandardCharsets.UTF_8).toString();
        return userInput.equals(cipherText);
    }
}
