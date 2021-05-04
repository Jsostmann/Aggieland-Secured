package com.aggieland.rest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class ChatRooms extends AggielandSecuredServlet {

  private static final Logger LOG = Logger.getLogger(ChatRooms.class.getName());

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession(false);

    if(session != null && !session.isNew()) {
      RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/JSP/chatrooms.jsp");
      rs.include(request, response);

    } else {
      LOG.info("Bad session redirecting to login");
      response.sendRedirect("signin");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
  }

}
