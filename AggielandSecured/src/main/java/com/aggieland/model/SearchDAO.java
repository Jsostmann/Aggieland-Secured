package com.aggieland.model;

import com.aggieland.database.DatabaseDAO;
import com.aggieland.database.DatabaseEnums;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchDAO extends BasicDAO {

  public SearchDAO(String databaseConnectionURL, String databaseUsername, String databasePassword) {
    super(databaseConnectionURL, databaseUsername, databasePassword);
  }

  public ArrayList<User> findUsers(HttpServletRequest request) throws SQLException {

    ArrayList<User> users = new ArrayList<>();

    String major = request.getParameter("major");
    String classification = request.getParameter("classification");
    String userName = request.getParameter("username");
    String searchValue = request.getParameter("search");

    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.USER_SEARCH_QUERY_2);


    statement.setString(1, userName != null ? String.valueOf(DatabaseEnums.ANY) : searchValue);
    statement.setString(2, !major.isEmpty() ? major : String.valueOf(DatabaseEnums.ANY));
    statement.setString(3, userName != null ? searchValue : String.valueOf(DatabaseEnums.ANY));

    ResultSet result = statement.executeQuery();

    while(result.next()) {
      users.add(User.createUserFromSearch(result));
    }

    System.out.println(users.size());

    disconnect();

    return null;
  }

}
