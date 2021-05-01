package com.aggieland.rest;

import java.io.*;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Class sends our users to the chatroom for discussions
 */
public class Chatroom extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Chatroom.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session != null && !session.isNew()) {
            RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/JSP/demochatroom.jsp");
            rs.include(request, response);

        } else {
            LOG.info("Bad session redirecting to login");
            response.sendRedirect("signin");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
