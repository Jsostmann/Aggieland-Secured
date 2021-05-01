package com.aggieland.websocket;

/**
 * Class to represent Messages
 */
public class Message {

  private MessageType messageType;
  private String message;

  public void setMessageType(MessageType m) {
    this.messageType = m;
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public void setMessage(String m) {
    this.message = m;
  }

  public String getMessage() {
    return this.message;
  }
}