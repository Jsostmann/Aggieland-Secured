package com.aggieland.auth;

import com.aggieland.websocket.ChatroomSocket;
import org.mindrot.jbcrypt.BCrypt;
import java.security.SecureRandom;
import java.util.logging.Logger;

public class AuthorizationDAO {

    private static final Logger LOG = Logger.getLogger(AuthorizationDAO.class.getName());
    private static final int SALT_ROUNDS = new SecureRandom().nextInt(30);


    public static String encrypt(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt(SALT_ROUNDS));
    }

    public static boolean verify(String plainText, String cipherText) {
        return BCrypt.checkpw(plainText, cipherText);
    }



}
