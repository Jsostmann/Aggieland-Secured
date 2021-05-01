package com.aggieland.model;

import com.aggieland.database.DatabaseDAO;
import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class handles all of the Queries for our Search servlet
 */
public class SearchDAO extends BasicDAO {

  public SearchDAO(String databaseConnectionURL, String databaseUsername, String databasePassword) {
    super(databaseConnectionURL, databaseUsername, databasePassword);
  }

  /**
   * This returns all users that match the query field filters from the webpage
   * @param request
   * @return
   * @throws SQLException
   */
  public ArrayList<User> findUsers(HttpServletRequest request) throws SQLException {

    ArrayList<User> users = new ArrayList<>();

    String major = request.getParameter("major");
    String classification = request.getParameter("classification");
    String userName = request.getParameter("username");
    String searchValue = request.getParameter("search").trim();

    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.USER_SEARCH_QUERY);

    searchValue = searchValue.isEmpty() ? "" : "%" + searchValue + "%";
    //statement.setString(1, userName != null ? "%" : !searchValue.isEmpty() ? searchValue : "%");
    statement.setString(1, userName != null ? "%" : searchValue);
    statement.setString(2, !major.isEmpty() ? major : "%");
    statement.setString(3, userName != null ? searchValue : "%");

    ResultSet result = statement.executeQuery();

    while(result.next()) {
      users.add(User.createUserFromSearch(result));
    }

    disconnect();

    return users;
  }

}
