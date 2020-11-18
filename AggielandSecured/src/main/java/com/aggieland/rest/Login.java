package com.aggieland.rest;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rs = request.getRequestDispatcher("html/index.html");
        rs.include(request, response);

    }
}