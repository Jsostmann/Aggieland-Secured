package com.aggieland.rest;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This Class forwards users to the signin page
 */
public class Home extends AggielandSecuredServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/signin");
    }
}
