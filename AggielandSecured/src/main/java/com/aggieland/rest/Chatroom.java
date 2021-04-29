package com.aggieland.rest;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Chatroom extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/JSP/demochatroom.jsp");
        rs.include(request, response);
        
    }
}
