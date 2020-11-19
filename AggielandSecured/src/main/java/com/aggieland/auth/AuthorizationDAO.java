package com.aggieland.auth;


import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class AuthorizationDAO {

    private static final Logger LOG = Logger.getLogger(AuthorizationDAO.class.getName());



    public static String hashEncrypt(String plainText) {
        return Hashing.sha512().hashString(plainText, StandardCharsets.UTF_8).toString();
    }

    public static boolean checkHashEncrypt(String plainText, String cipherText) {
        String userInput = Hashing.sha512().hashString(plainText, StandardCharsets.UTF_8).toString();
        return userInput.equals(cipherText);
    }



}
