package com.aggieland.rest;

import com.aggieland.model.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.logging.Logger;

public class Search extends AggielandSecuredServlet {

  UserDAO userDAO;
  private static final Logger LOG = Logger.getLogger(Search.class.getName());


  @Override
  public void init() throws ServletException {
    super.init();
    userDAO = new UserDAO(getDatabaseConnectionURL(),getDatabaseUsername(),getDatabasePassword());
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession currentSession = request.getSession(false);

    if(currentSession != null && !currentSession.isNew()) {

      LOG.info("Session is good, continue");
      RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/JSP/Search2.jsp");
      rs.include(request, response);

    } else {
      LOG.info("Session is bad, redirect to login");
      response.sendRedirect("signin");
    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession currentSession = request.getSession(false);

    if(currentSession != null && !currentSession.isNew()) {

      String majorfilter = request.getParameter("major");
      String classificationFilter = request.getParameter("classification");


      request.setAttribute("filter", majorfilter);

      RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/JSP/search.jsp");
      rs.forward(request, response);

    } else {
      LOG.info("Session is bad, redirect to login");
      response.sendRedirect("signin");
    }

  }
}
