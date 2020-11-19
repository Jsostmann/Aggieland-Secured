package com.aggieland.websocket;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/")
public class ChatroomSocket {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
    private static final Logger LOG = Logger.getLogger(ChatroomSocket.class.getName());

    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
        LOG.log(Level.INFO, "Connection opened");
        sessions.add(session);
        session.getBasicRemote().sendText("(Server): Welcome to the AggieLandSecure Chatroom. Please state your username to begin.");

        for(Session users: session.getOpenSessions()) {
            users.getBasicRemote().sendText("A new user joined: " + session.getId());
        }
    }

    @OnClose
    public void close(Session session) throws IOException, EncodeException {

    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {

    }

}
