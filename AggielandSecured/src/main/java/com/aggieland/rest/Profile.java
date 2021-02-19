package com.aggieland.rest;

import com.aggieland.model.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Profile extends HttpServlet {
    UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        String databaseConnectionURL = getServletContext().getInitParameter("databaseURL");
        String databaseUsername = getServletContext().getInitParameter("databaseUsername");
        String databasePassword = getServletContext().getInitParameter("databasePassword");

        userDAO = new UserDAO(databaseConnectionURL, databaseUsername, databasePassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession a = request.getSession(false);
        if(a != null && !a.isNew()) {
            System.out.println("good");
            RequestDispatcher rs = request.getRequestDispatcher("html/Profile.jsp");
            rs.include(request, response);
        } else {
            System.out.println("bad");
            response.sendRedirect("signin");
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
