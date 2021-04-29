package com.aggieland.rest;


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
            LOG.info(String.valueOf(a.getAttribute("user")));

            RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/JSP/profile.jsp");

            if(request.getParameter("from-list") != null) {
                deleteUser(request,response);
            }

            User me = (User)a.getAttribute("user");
            ArrayList<User> friends = null;

            if(me != null) {

                try {

                    friends = userDAO.getFriends(me.getUserId());

                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }

            request.setAttribute("friends",friends);
            rs.include(request, response);

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
            doGet(request,response);
            //response.sendRedirect("profile");

        } else {
            System.out.println("bad");
            response.sendRedirect("signin");
        }

    }

    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User searchedUser = userDAO.getUser(request.getParameter("from-list"));
            User me = (User) request.getSession().getAttribute("user");
            userDAO.removeFriend(searchedUser.getUserId(),me.getUserId());
            response.sendRedirect("profile");

        } catch (SQLException e) {
            RequestDispatcher rs = request.getRequestDispatcher("html/500.html");
            rs.include(request, response);
            e.printStackTrace();
        }

    }
}
