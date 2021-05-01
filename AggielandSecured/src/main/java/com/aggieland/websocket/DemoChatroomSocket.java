package com.aggieland.websocket;


import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.aggieland.rest.Signin;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This serves as the Demo Chatroom for our demonstration, currently working on additional endpoints
 */
@ServerEndpoint(value = "/demo")

public class DemoChatroomSocket{

  private Logger log = Logger.getLogger(DemoChatroomSocket.class.getName());
  private DemoRoom room = DemoRoom.getRoom();

  /**
   * Not used, instead decided to use onMessage
   * @param session
   * @param config
   */
  @OnOpen
  public void onOpen(final Session session, EndpointConfig config) {}


  /**
   * grabs the session and message from client and adds them
   * to the static room of users
   * @param session
   * @param messageJson
   * @throws IOException
   */
  @OnMessage
  public void onMessage(final Session session, final String messageJson) throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    Message message = null;

    try {
      message = mapper.readValue(messageJson, Message.class);

    } catch (IOException e) {
      String errorMessage = "Badly formatted message";
      try {
        session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, errorMessage));
      } catch (IOException ex) {
        log.severe(ex.getMessage());
      }
    }

    Map<String, Object> properties = session.getUserProperties();

    if (message.getMessageType() == MessageType.ENTER) {

      String userName = message.getMessage();

      properties.put("userName", userName);
      String users = room.getUsers();

      room.join(session);
      room.sendMessage(userName + " - Joined");

      if(users.length() > 0) {
        System.out.println(users);
        session.getBasicRemote().sendText(users);
      }
    }

    else {

      String userName = (String)properties.get("userName");
      room.sendMessage(userName + ": " + message.getMessage());
    }
  }

  @OnClose
  public void OnClose(Session session, CloseReason reason) {
    room.leave(session);
    room.sendMessage((String)session.getUserProperties().get("userName") + "  Left");
  }

  @OnError
  public void onError(Session session, Throwable ex) { log.info("Error: " + ex.getMessage()); }
}