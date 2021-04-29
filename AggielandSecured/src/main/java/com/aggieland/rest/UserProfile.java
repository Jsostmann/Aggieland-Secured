package com.aggieland.rest;

import com.aggieland.database.DatabaseDAO;
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

      System.out.println(request.getQueryString());

      String searchedUserName = request.getPathInfo().replace("/","");

      RequestDispatcher rs;

      try {

        User searchedUser = userDAO.getUser(searchedUserName);
        User me = (User) a.getAttribute("user");

        long friendSignifier = userDAO.areFriends(searchedUser.getUserId(),me.getUserId());

        String friendStatus;

        if(friendSignifier == DatabaseDAO.IS_FRIEND) {
          friendStatus = "is-friend";

        } else if(friendSignifier == DatabaseDAO.PENDING_FRIEND) {
          friendStatus = "pending-friend";

        } else {
          friendStatus = "not-friend";

        }

        request.setAttribute("friendStatus",friendStatus);
        request.setAttribute("searchedUser",searchedUser);
        rs = request.getRequestDispatcher("/WEB-INF/JSP/profile2.jsp");
        rs.forward(request,response);

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

    HttpSession a = request.getSession(false);

    if(a != null && !a.isNew()) {
      LOG.info("Session is good");

      try {

        String searchedUserName = request.getPathInfo().replace("/","");

        User searchedUser = userDAO.getUser(searchedUserName);
        User me = (User) a.getAttribute("user");

        long friendSignifier = userDAO.areFriends(searchedUser.getUserId(),me.getUserId());


        if(friendSignifier == DatabaseDAO.IS_FRIEND) {
          userDAO.removeFriend(searchedUser.getUserId(),me.getUserId());

        } else {
          userDAO.addFriend(searchedUser.getUserId(),me.getUserId());
        }

        processRequest(request,response);

      } catch (SQLException e) {
        e.printStackTrace();
      }

    } else {
      LOG.info("Session is bad, redirecting back home");
      response.sendRedirect(request.getContextPath() + "/signin");
    }

  }

}