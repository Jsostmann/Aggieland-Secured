package com.aggieland.rest;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class Chatroom extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        RequestDispatcher rs = request.getRequestDispatcher("html/Chatroom.html");
        rs.include(request, response);

    }
}