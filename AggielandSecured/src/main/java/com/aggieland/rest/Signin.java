package com.aggieland.rest;

import com.aggieland.model.User;
import com.aggieland.model.UserDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Class handles the Authentication and Authorization to the website and checks
 * to see if a valid session already exists thus sending them to the profile page
 */
public class Signin extends AggielandSecuredServlet {
    UserDAO userDAO;
    private static final Logger LOG = Logger.getLogger(Signin.class.getName());
    public static final ConcurrentHashMap<String,HttpSession> activeUsers = new ConcurrentHashMap<>();

  @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO(getDatabaseConnectionURL(),getDatabaseUsername(),getDatabasePassword());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        RequestDispatcher serverResponse;

        if(session != null && !session.isNew()) {
          response.sendRedirect("profile");
            
        } else {
          serverResponse = request.getRequestDispatcher("html/Signin.html");
          serverResponse.include(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher serverResponse;

        try {
            boolean successfulLogin = userDAO.verifiedUser(request.getParameter("userName"),request.getParameter("password"));

            if(successfulLogin) {
                LOG.info("Successful login, Creating new session");
                User user = userDAO.getUser(request.getParameter("userName"));
                HttpSession session = request.getSession(true);
                Cookie c = new Cookie("user",user.getUserName());
                response.addCookie(c);
                activeUsers.put(user.getUserName(),session);
                session.setMaxInactiveInterval(600);
                session.setAttribute("user",user);
                response.sendRedirect("profile");
                
            } else {
              LOG.info("Failed login, Redirecting to signup");
              response.sendRedirect("signup");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            serverResponse = request.getRequestDispatcher("html/500.html");
            serverResponse.forward(request,response);
        }

    }
}
