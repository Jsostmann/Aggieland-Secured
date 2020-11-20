package com.aggieland.rest;

import com.aggieland.auth.AuthorizationDAO;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;



public class Signin extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher rs = request.getRequestDispatcher("html/Signin.html");
        rs.include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher rs = request.getRequestDispatcher("html/Signin.html");
        rs.include(request, response);

    }
}