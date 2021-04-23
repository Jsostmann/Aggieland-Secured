package com.aggieland.rest;

import com.aggieland.model.User;
import com.aggieland.model.UserDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
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

  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession a = request.getSession(false);

    if(a != null && !a.isNew()) {
      LOG.info("Session is good, continue");
      String searchedUserName = request.getPathInfo().replace("/","");
      RequestDispatcher rs;

      try {

        User searchedUser = userDAO.getUser(searchedUserName);
        User me = (User) a.getAttribute("user");



        request.setAttribute("searchedUser",searchedUser);
        rs = request.getRequestDispatcher("/WEB-INF/JSP/profile2.jsp");
        rs.include(request,response);

      } catch (SQLException e) {
        e.printStackTrace();
      }


    } else {
      LOG.info("Session is bad, redirecting back home");
      response.sendRedirect(request.getContextPath() + "/signin");
    }

  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);

  }

}