package com.aggieland.rest;

import com.aggieland.model.User;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This Class handles the logging out of users
 */
public class Logout extends AggielandSecuredServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if (session != null) {
      User me = (User)session.getAttribute("user");
      Signin.activeUsers.remove(me.getUserName());
      session.removeAttribute("user");
      session.invalidate();
    }
    response.sendRedirect("signin");
  }

}
