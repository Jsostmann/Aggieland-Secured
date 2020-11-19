package com.aggieland.rest;

import com.aggieland.auth.AuthorizationDAO;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;



public class Signin extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");

        response.setContentType("text/html");

        response.getWriter().append(userName).append(" | ").append(passWord);

        //RequestDispatcher rs = request.getRequestDispatcher("html/Signin.html");
        //rs.include(request, response);

    }
}