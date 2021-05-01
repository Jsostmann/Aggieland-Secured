package com.aggieland.database;

import java.sql.*;

/**
 * This class contains all of the various Query Strings we will make to the Database
 */
public class DatabaseDAO {

  private static final String DB_URL = "jdbc:mysql://localhost:8002/aggielandsql";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "root";
  static Connection databaseConnection = null;
  private static Statement statement = null;

  public static final String DELETE_FRIEND_QUERY = "DELETE from friendship WHERE (user_one_id = ? AND user_two_id = ?)";
  public static final String UPDATE_FRIEND_QUERY = "UPDATE friendship SET status = ?, recent_user_id = ? WHERE (user_one_id = ? AND user_two_id = ?)";
  public static final String ADD_FRIEND_QUERY = "INSERT INTO friendship (user_one_id, user_two_id, recent_user_id, status) VALUES(?, ?, ?, ?)";

  public static final String ARE_FRIENDS_QUERY = "SELECT * from friendship WHERE (user_one_id = ? AND user_two_id = ?)";
  public static final String GET_FRIENDS_QUERY = "SELECT * FROM friendship WHERE (user_one_id = ? OR user_two_id = ?) AND status = 1";
  public static final String GET_PENDING_FRIENDS_QUERY = "SELECT * FROM friendship WHERE (user_one_id = ? OR user_two_id = ?) AND status = 0";

  public static final String USER_SEARCH_QUERY = "SELECT user_id, user_name, first_name, last_name, email, major from user WHERE (first_name LIKE ? AND major LIKE ? AND user_name LIKE ?)";
  public static final String UPDATE_USER_PASSWORD_QUERY = "UPDATE user SET first_name = ?, last_name = ?, email = ?, password= ?, password_salt= ?, profile_picture = ?, user_info = ?, major = ? WHERE user_id = ?;";
  public static final String UPDATE_USER_QUERY = "UPDATE user SET first_name = ?, last_name = ?, email = ?, profile_picture = ?, user_info = ?, major = ? WHERE user_id = ?;";
  public static final String GET_USER_QUERY = "SELECT * FROM user WHERE user_name = ? and pass_word = ?";
  public static final String ADD_USER_QUERY = "INSERT INTO user (first_name, last_name, user_name, password, password_salt, email, date_added, profile_picture, user_info, major) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  public static final String ADD_USER_QUERY_OLD = "INSERT INTO user (first_name, last_name, user_name, password, password_salt, email, date_added, profile_picture, user_info, major) values(?, ?, ?, ?, ?, ?, CURRENT_DATE, ?, ?, ?)";
  public static final String FIND_USER_QUERY = "SELECT * FROM user WHERE user_name = ?";
  public static final String FIND_USER_BY_ID = "SELECT user_id, user_name, first_name, last_name, email, major FROM user WHERE user_id = ?";

  public static final int IS_FRIEND = 1;
  public static final int PENDING_FRIEND = 0;
  public static final int NOT_FRIEND = -1;
  public static final String TEST_DATE_QUERY = "SELECT CURRENT_DATE";

  public DatabaseDAO() {

  }

  static synchronized void connect() {
    try {

      Class.forName("com.mysql.cj.jdbc.Driver");
      databaseConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      statement = databaseConnection.createStatement();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
