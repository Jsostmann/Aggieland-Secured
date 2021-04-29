package com.aggieland.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

public class DemoRoom {

  private static DemoRoom instance = null;
  private List<Session> sessions = new ArrayList<Session>();

  public synchronized void join(Session session) {
    sessions.add(session);
  }
  public synchronized void leave(Session session) {
    sessions.remove(session);
  }

  public synchronized void sendMessage(String message) {
    for (Session session: sessions) {
      if (session.isOpen()) {
        try { session.getBasicRemote().sendText(message); }
        catch (IOException e) { e.printStackTrace(); }
      }
    }
  }

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

  public synchronized static DemoRoom getRoom() {
    if (instance == null) {
      instance = new DemoRoom();
    }
    return instance;
  }
}