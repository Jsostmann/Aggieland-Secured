package com.aggieland.rest;

import com.aggieland.database.DatabaseDAO;
import com.aggieland.model.User;
import com.aggieland.model.UserDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Manages Other User Profiles other than the person signed in
 */
public class UserProfile extends AggielandSecuredServlet {
  private static final Logger LOG = Logger.getLogger(UserProfile.class.getName());
  private UserDAO userDAO;


  @Override
  public void init() throws ServletException {
    super.init();
    userDAO = new UserDAO(getDatabaseConnectionURL(),getDatabaseUsername(),getDatabasePassword());
  }

  /**
   * Processes our request and determines the friend status of that user
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession a = request.getSession(false);

    if(a != null && !a.isNew()) {

      LOG.info("Session is good, continue");

      String searchedUserName = request.getPathInfo().replace("/","");

      RequestDispatcher rs;

      try {
        User searchedUser = userDAO.getUser(searchedUserName);
        User me = (User)a.getAttribute("user");

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

  /**
   * handles the update of a friend or deletes them
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
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