package com.aggieland.rest;

import com.aggieland.model.User;
import com.aggieland.model.UserDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;


public class Signup extends AggielandSecuredServlet{

    UserDAO userDAO;
    private static final Logger LOG = Logger.getLogger(Signup.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO(getDatabaseConnectionURL(),getDatabaseUsername(),getDatabasePassword());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher rs = request.getRequestDispatcher("html/Signup.html");
        rs.include(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher serverResponse;
        try {

            //boolean success = signUpNewUser(request);
             User user = userDAO.addUser(request);

             boolean success = user != null;

            if (success) {
                LOG.info("user sucessfully signed up, proceed to profile");
                request.getSession().setAttribute("user",user);
                System.out.println(user);
                response.sendRedirect("/AggielandSecured/profile");

            } else {
                LOG.info("user already exists, redirect to signin");
                response.sendRedirect("/AggielandSecured/signin");
            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            serverResponse = request.getRequestDispatcher("html/500.html");
            serverResponse.forward(request,response);
        }
    }

    private boolean signUpNewUser(HttpServletRequest request) throws SQLException, IOException {

        String password = request.getParameter("password");

        User addedUser = User.createUser(request);

        if (userDAO.userExists(addedUser.getUserName())) {
            return false;
        }

        //request.getSession().setAttribute("user",addedUser);
        return false;
        //return userDAO.addUser(addedUser, password);
    }
}