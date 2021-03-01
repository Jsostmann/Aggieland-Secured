package com.aggieland.rest;

import com.aggieland.model.User;
import com.aggieland.model.UserDAO;

import java.io.*;
import java.sql.SQLException;


import javax.servlet.*;
import javax.servlet.http.*;

public class Signin extends HttpServlet {

    UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        String databaseConnectionURL = getServletContext().getInitParameter("databaseURL");
        String databaseUsername = getServletContext().getInitParameter("databaseUsername");
        String databasePassword = getServletContext().getInitParameter("databasePassword");

        userDAO = new UserDAO(databaseConnectionURL, databaseUsername, databasePassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rs = request.getRequestDispatcher("html/Signin.html");
        rs.include(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            boolean successfulLogin = signInUser(request);

            if(successfulLogin) {
                HttpSession currentSession = request.getSession(true);
                currentSession.setMaxInactiveInterval(10);
                response.sendRedirect("/aggielandsecured/profile");
                //RequestDispatcher rs = request.getRequestDispatcher("html/Profile.jsp");
                //rs.include(request, response);
            } else {
                response.sendRedirect("/aggielandsecured/signup");
            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

    }

    private boolean signInUser(HttpServletRequest request) throws SQLException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        User user = null; 

//        if(userDAO.userExists(userName) && userDAO.verifiedUser(u)) {
            
  //      }
        if(userDAO.verifiedUser(userName,password)) {
            user = userDAO.getUser(userName);
            request.getSession().setAttribute("user",user);
        }
        return user != null;
    }
}