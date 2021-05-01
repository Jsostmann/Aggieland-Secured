package com.aggieland.rest;

import com.aggieland.model.SearchDAO;
import com.aggieland.model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This Class handles all of the user searches that you can make
 */
public class Search extends AggielandSecuredServlet {
  SearchDAO searchDAO;
  private static final Logger LOG = Logger.getLogger(Search.class.getName());

  @Override
  public void init() throws ServletException {
    super.init();
    searchDAO = new SearchDAO(getDatabaseConnectionURL(),getDatabaseUsername(),getDatabasePassword());
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if(session != null && !session.isNew()) {
      LOG.info("Session is good, continue");
      RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/JSP/search.jsp");
      rs.include(request, response);
      
    } else {
      LOG.info("Session is bad, redirect to login");
      response.sendRedirect("signin");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if(session != null && !session.isNew()) {
      ArrayList<User> results = null;
      
      try {
        results = searchDAO.findUsers(request);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      
      request.setAttribute("results",results);
      RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/JSP/search.jsp");
      rs.forward(request, response);
    } else {
      LOG.info("Session is bad, redirect to login");
      response.sendRedirect("signin");
    }
  }
}
