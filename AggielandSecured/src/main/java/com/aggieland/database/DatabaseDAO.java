package com.aggieland.database;


import java.sql.*;


public class DatabaseDAO {

  private static final String DB_URL = "jdbc:mysql://localhost:8002/aggielandsql";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "root";
  private static Connection databaseConnection = null;
  private static Statement statement = null;
  private static PreparedStatement st = null;
  private static ResultSet queryResult = null;
  private static String query = null;

  public static final String UPDATE_USER_QUERY = "UPDATE user SET first_name = ?, last_name = ?, email = ?, profile_picture = ?, user_info = ?, major = ? WHERE user_id = ?;";
  public static final String GET_USER_QUERY = "SELECT * FROM user WHERE user_name = ? and pass_word = ?";
  public static final String USER_SEARCH_QUERY = "SELECT user_id,user_name,concat(first_name,' ',last_name),email,major FROM user WHERE user_name LIKE ? AND major LIKE ?";
  public static final String ADD_USER_QUERY = "INSERT INTO user (first_name, last_name, user_name, password, password_salt, email, date_added, profile_picture, user_info, major) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                                                                                                                                                                             //1, 2, 3, 4, 5  6  7  8  9  10
  public static final String ADD_USER_QUERY_OLD = "INSERT INTO user (first_name, last_name, user_name, password, password_salt, email, date_added, profile_picture, user_info, major) values(?, ?, ?, ?, ?, ?, CURRENT_DATE, ?, ?, ?)";
  public static final String FIND_USER_QUERY = "SELECT * FROM user WHERE user_name = ?";

  public static final String test = "SELECT CURRENT_DATE";

  public DatabaseDAO() {

    try {

      //Class.forName("com.mysql.jdbc.Driver");
      //databaseConnection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
      //statement = databaseConnection.createStatement();

      queryResult = statement.executeQuery(query);

      while (queryResult.next()) {

        for (int i = 0; i < 6; i++) {

          System.out.print(queryResult.getString(i + 1) + " | ");

        }
        System.out.println();
      }

      databaseConnection.close();

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static synchronized void connect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      databaseConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      statement = databaseConnection.createStatement();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private static synchronized void disconnect() throws SQLException {
    databaseConnection.close();

  }

  public static String getDbUrl() {
    return DB_URL;
  }

  public static String getDbUsername() {
    return DB_USERNAME;
  }

  public static String getDbPassword() {
    return DB_PASSWORD;
  }

  public static void main(String[] args) throws SQLException {

    // test properties file



    connect();
    PreparedStatement statement = databaseConnection.prepareStatement(DatabaseDAO.USER_SEARCH_QUERY);
    statement.setString(1,"%");
    statement.setString(2,"%");
    queryResult = statement.executeQuery();

    System.out.println("User_id\tuser_name\tfull_name\temail\tmajor");
    while(queryResult.next()) {

      System.out.print(queryResult.getString(1) + "\t");
      System.out.print(queryResult.getString(2) + "\t");
      System.out.print(queryResult.getString(3) + "\t");
      System.out.print(queryResult.getString(4) + "\t");
      System.out.print(queryResult.getString(5) + "\t");
      System.out.println();
    }

    disconnect();


  }

}
