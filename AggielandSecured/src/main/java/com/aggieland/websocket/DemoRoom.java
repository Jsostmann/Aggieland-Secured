package com.aggieland.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;

/**
 * Class for our Room of users
 */
public class DemoRoom {
  private static DemoRoom instance = null;
  private List<Session> sessions = new ArrayList<>();

  /**
   * adds a user to this demo room
   * @param session
   */
  public synchronized void join(Session session) {
    sessions.add(session);
  }

  /**
   * Removes user from this Room
   * @param session
   */
  public synchronized void leave(Session session) {
    sessions.remove(session);
  }

  /**
   * Sends a message to all other users in this Room
   * @param message
   */
  public synchronized void sendMessage(String message) {
    for (Session session: sessions) {
      if (session.isOpen()) {
        try {
          session.getBasicRemote().sendText(message);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Gets all users in the room
   * @return
   */
  public synchronized String getUsers() {
    String result = "";
    for (Session session: sessions) {
      if (session.isOpen()) {
            String temp = (String)session.getUserProperties().get("userName");
            result += temp + System.lineSeparator();
      }
    }
    return result;
  }

  /**
   * returns an instance of this DemoRoom
   * @return
   */
  public synchronized static DemoRoom getRoom() {
    if (instance == null) {
      instance = new DemoRoom();
    }
    return instance;
  }
}