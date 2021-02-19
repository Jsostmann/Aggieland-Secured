package com.aggieland.rest;


import com.aggieland.database.DatabaseDAO;
import com.aggieland.model.User;
import com.aggieland.model.UserDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;


public class Signup extends HttpServlet {

    UserDAO userDAO;
    private static final Logger LOG = Logger.getLogger(Signup.class.getName());

    @Override
    public void init() throws ServletException {

        String databaseConnectionURL = getServletContext().getInitParameter("databaseURL");
        String databaseUsername = getServletContext().getInitParameter("databaseUsername");
        String databasePassword = getServletContext().getInitParameter("databasePassword");

        userDAO = new UserDAO(databaseConnectionURL, databaseUsername, databasePassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher rs = request.getRequestDispatcher("html/Signup.html");
        rs.include(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            boolean sucess = signUpNewUser(request);

            if (sucess) {
                RequestDispatcher rs = request.getRequestDispatcher("html/Profile.jsp");
                rs.include(request, response);

            } else {
                LOG.info("user already exists");
                response.sendRedirect("/aggielandsecured/signin");
            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
    }

    private boolean signUpNewUser(HttpServletRequest request) throws SQLException {

        String password = request.getParameter("password");



        User addedUser = User.createUser(request);

        if (userDAO.userExists(addedUser.getUserName())) {
            return false;
        }

        request.getSession().setAttribute("user",addedUser);

        return userDAO.addUser(addedUser, password);
    }
}