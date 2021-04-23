package com.aggieland.rest;


import com.aggieland.model.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Profile extends AggielandSecuredServlet{

    UserDAO userDAO;
    private static final Logger LOG = Logger.getLogger(Profile.class.getName());


    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO(getDatabaseConnectionURL(),getDatabaseUsername(),getDatabasePassword());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession a = request.getSession(false);

        if(a != null && !a.isNew()) {
            LOG.info("Session is good, continue");
            RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/JSP/profile.jsp");
            rs.include(request, response);
            System.out.println(a.getAttribute("user"));
        } else {
            LOG.info("Session is bad, redirect to login");
            response.sendRedirect("signin");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession a = request.getSession(false);

        if(a != null && !a.isNew()) {

            try {
                userDAO.updateAccount(request);
                System.out.println(a.getAttribute("user"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect("profile");

        } else {
            System.out.println("bad");
            response.sendRedirect("signin");
        }

    }
}
