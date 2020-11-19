package com.aggieland.rest;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class Signup extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        RequestDispatcher rs = request.getRequestDispatcher("html/Signup.html");
        rs.include(request, response);

    }
}