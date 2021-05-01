package com.aggieland.rest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to Represent our Servlet with a reference to
 * our database connection URL, user, and password
 */

abstract class AggielandSecuredServlet extends HttpServlet {
  private String databaseConnectionURL;
  private String databaseUsername;
  private String databasePassword;

  /**
   * Called when Servlet is created
   * @throws ServletException
   */
  @Override
  public void init() throws ServletException {
    databaseConnectionURL = getServletContext().getInitParameter("databaseURL");
    databaseUsername = getServletContext().getInitParameter("databaseUsername");
    databasePassword = getServletContext().getInitParameter("databasePassword");

    Properties props = new Properties();
    InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config.properties");

    try {
      props.load(input);
      String url = props.getProperty("databaseURL");
      String username = props.getProperty("databaseUsername");
      String password = props.getProperty("databasePassword");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getDatabaseConnectionURL() {
    return this.databaseConnectionURL;
  }

  public String getDatabaseUsername() {
    return this.databaseUsername;
  }

  public String getDatabasePassword() {
    return databasePassword;
  }
}
