package com.aggieland.rest;

import com.aggieland.database.DatabaseDAO;
import com.aggieland.model.User;
import com.aggieland.model.UserDAO;
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
 * This class does the majority of the handling for our profile page
 */
public class Profile extends AggielandSecuredServlet{
    UserDAO userDAO;
    private static final Logger LOG = Logger.getLogger(Profile.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO(getDatabaseConnectionURL(),getDatabaseUsername(),getDatabasePassword());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if(session != null && !session.isNew()) {
            
            LOG.info("Valid session continue");
            RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/JSP/profile.jsp");

            if(request.getParameter("from-list") != null) {
                deleteUser(request,response);
            }

            User me = (User)session.getAttribute("user");
            ArrayList<User> friends = null;

            ArrayList<User> pendingFriends = null;

            if(me != null) {
                try {
                    friends = userDAO.getFriends(me.getUserId());
                    pendingFriends = userDAO.getPendingFriends(me.getUserId());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            request.setAttribute("friends",friends);
            request.setAttribute("pendingFriends",pendingFriends);
            rs.include(request, response);

        } else {
            LOG.info("Bad session redirecting to login");
            response.sendRedirect("signin");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if(session != null && !session.isNew()) {
            try {
                userDAO.updateAccount(request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            doGet(request,response);

        } else {
            LOG.info("Bad session redirecting to login");
            response.sendRedirect("signin");
        }
    }

    /**
     * This function removes/accepts stale friend requests and allows you to delete friends
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            User searchedUser = userDAO.getUser(request.getParameter("from-list"));

            User me = (User) request.getSession().getAttribute("user");

            long friendSignifier = userDAO.areFriends(searchedUser.getUserId(),me.getUserId());

            if(friendSignifier == DatabaseDAO.IS_FRIEND) {
              userDAO.removeFriend(searchedUser.getUserId(),me.getUserId());
            }

            if(request.getParameter("action" ) != null) {

                int action = Integer.parseInt(request.getParameter("action"));

                if(friendSignifier == DatabaseDAO.PENDING_FRIEND && action == 0){
                    userDAO.removeFriend(searchedUser.getUserId(),me.getUserId());

                } else {
                    userDAO.updateFriendStatus(searchedUser.getUserId(),me.getUserId(),1);
                }
            }

            response.sendRedirect("profile");

        } catch (Exception e) {

          if(e instanceof SQLException) {
            SQLException error = (SQLException) e;
            error.printStackTrace();

          }else if(e instanceof NullPointerException) {
            NullPointerException error = (NullPointerException) e;
            error.printStackTrace();

          } else if(e instanceof NumberFormatException) {
              NumberFormatException error = (NumberFormatException) e;
              error.printStackTrace();

          } else {
            e.printStackTrace();
          }

          RequestDispatcher rs = request.getRequestDispatcher("html/500.html");
          rs.include(request, response);
        }
    }
}
