package com.aggieland.rest;


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class Logout extends AggielandSecuredServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if (session != null) {
      session.removeAttribute("user");
      session.invalidate();
    }

    response.sendRedirect("signin");


  }

}