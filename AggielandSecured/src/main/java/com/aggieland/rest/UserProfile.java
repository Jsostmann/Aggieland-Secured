package com.aggieland.rest;

import com.aggieland.model.UserDAO;
import java.io.*;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;


public class UserProfile extends AggielandSecuredServlet {

  private static final Logger LOG = Logger.getLogger(UserProfile.class.getName());
  private UserDAO userDAO;


  @Override
  public void init() throws ServletException {
    super.init();
    userDAO = new UserDAO(getDatabaseConnectionURL(),getDatabaseUsername(),getDatabasePassword());
  }

  public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession a = request.getSession(false);

    if(a != null && !a.isNew()) {
      LOG.info("Session is good, continue");

      String searchedUser = request.getPathInfo().replace("/","");
      System.out.println(searchedUser);



    } else {
      LOG.info("Session is bad, redirecting back home");
      response.sendRedirect(request.getContextPath() + "/signin");
    }

  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    processRequest(request,response);

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) {

  }

}