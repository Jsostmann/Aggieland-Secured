package com.aggieland.model;

import com.aggieland.auth.AuthoizationUtil;
import com.aggieland.database.DatabaseDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Class for all of our user CRUD operations to the database
 */
public class UserDAO extends BasicDAO {
  private static final Logger LOG = Logger.getLogger(UserDAO.class.getName());

  public UserDAO(String databaseConnectionURL, String databaseUsername, String databasePassword) {
    super(databaseConnectionURL, databaseUsername, databasePassword);
  }

  /**
   * Verifys user is real
   * @param userName
   * @param password
   * @return
   * @throws SQLException
   */
  public boolean verifiedUser(String userName, String password) throws SQLException {

    boolean isVerified = false;

    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.FIND_USER_QUERY);

    statement.setString(1, userName);

    ResultSet result = statement.executeQuery();

    if (result.next()) {

      String dbSaltedPassword = result.getString(6);
      String dbSalt = result.getString(7);

      StringBuilder enteredSaltedPassword = new StringBuilder(300);
      enteredSaltedPassword.append(password);
      enteredSaltedPassword.append(dbSalt);

      isVerified = AuthoizationUtil.checkPassword(enteredSaltedPassword.toString(), dbSaltedPassword);

      System.out.println(isVerified);


    }

    disconnect();

    return isVerified;
  }

  public boolean userExists(String userName) throws SQLException, IOException {

    return getUser(userName) != null;

  }

  /**
   * Returns user from database
   * @param userName
   * @return
   * @throws SQLException
   * @throws IOException
   */
  public User getUser(String userName) throws SQLException, IOException {

    User foundUser = null;

    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.FIND_USER_QUERY);

    statement.setString(1, userName);

    ResultSet result = statement.executeQuery();

    if (result.next()) {
      foundUser = User.createUser(result);
    }

    disconnect();

    return foundUser;
  }

  /**
   * Adds new user to database
   * @param request
   * @return
   * @throws SQLException
   */
  public User addUser(HttpServletRequest request) throws SQLException {

    connect();

    User user = User.createUser(request);

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.ADD_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

    String password = request.getParameter("password").trim();

    String salt = AuthoizationUtil.generateSalt();
    StringBuilder saltedPassword = new StringBuilder(50);
    saltedPassword.append(password);
    saltedPassword.append(salt);

    String hashedPassword = AuthoizationUtil.hashSaltedPassword(saltedPassword.toString(), salt);

    statement.setString(1, user.getFirstName());
    statement.setString(2, user.getLastName());
    statement.setString(3, user.getUserName());
    statement.setString(4, hashedPassword);
    statement.setString(5, salt);
    statement.setString(6, user.getEmail());
    statement.setDate(7, user.getDateCreatedAsDate());
    statement.setNull(8, Types.NULL);
    statement.setNull(9, Types.NULL);
    statement.setString(10, user.getMajor());

    boolean userAdded = statement.executeUpdate() > 0;

    ResultSet primaryKey = statement.getGeneratedKeys();

    long userId = -1;

    if (primaryKey.next()) {
      userId = primaryKey.getInt(1);
    }

    user.setUserId(userId);

    disconnect();

    return userAdded ? user : null;
  }

  /**
   * Updates old user
   * @param request
   * @return
   * @throws SQLException
   * @throws IOException
   * @throws ServletException
   */
  public User updateAccount(HttpServletRequest request) throws SQLException, IOException, ServletException {
    User updatedUser = null;

    User.updateUser(request);

    connect();

    String updateQuery;
    String hashedPassword = null;
    String salt = null;

    String password = request.getParameter("password").trim();

    if(!password.isEmpty()) {

      updateQuery = DatabaseDAO.UPDATE_USER_PASSWORD_QUERY;
      salt = AuthoizationUtil.generateSalt();
      StringBuilder saltedPassword = new StringBuilder(50);
      saltedPassword.append(password);
      saltedPassword.append(salt);
      hashedPassword = AuthoizationUtil.hashSaltedPassword(saltedPassword.toString(), salt);

    }else {
      updateQuery = DatabaseDAO.UPDATE_USER_QUERY;
    }

    PreparedStatement statement = getDatabaseConnection().prepareStatement(updateQuery);

    updatedUser = (User) request.getSession(false).getAttribute("user");

    int parameter = 1;

    statement.setString(parameter++, updatedUser.getFirstName());
    statement.setString(parameter++, updatedUser.getLastName());
    statement.setString(parameter++, updatedUser.getEmail());

    if(!password.isEmpty()) {
      statement.setString(parameter++, hashedPassword);
      statement.setString(parameter++, salt);

    } else {
      parameter = 4;
    }

    statement.setString(parameter++, updatedUser.getProfilePictureBase64());
    statement.setString(parameter++, updatedUser.getUserInfo());
    statement.setString(parameter++, updatedUser.getMajor());
    statement.setLong(parameter, updatedUser.getUserId());

    boolean userUpdated = statement.executeUpdate() > 0;

    if (userUpdated) {
      LOG.info("Succesfully updated user");

    } else {
      LOG.info("Failed to update user");
    }

    disconnect();

    return updatedUser;
  }

  /**
   * Returns user based on ID
   * @param userID
   * @return
   * @throws SQLException
   * @throws IOException
   */
  public User getUser(long userID) throws SQLException, IOException {

    User foundUser = null;

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.FIND_USER_BY_ID);

    statement.setLong(1, userID);

    ResultSet result = statement.executeQuery();

    if (result.next()) {
      foundUser = User.createUserFromSearch(result);
    }

    return foundUser;
  }

  /**
   * Gets friends of user based on ID
   * @param userID
   * @return
   * @throws SQLException
   * @throws IOException
   */
  public ArrayList<User> getFriends(long userID) throws SQLException, IOException {

    ArrayList<User> friends = new ArrayList<>();

    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.GET_FRIENDS_QUERY);

    statement.setLong(1, userID);
    statement.setLong(2, userID);

    ResultSet results = statement.executeQuery();

    while (results.next()) {

      long friendID = results.getLong(1) != userID ? results.getLong(1) : results.getLong(2);

      if (results.getLong(4) == DatabaseDAO.IS_FRIEND) {

        User currentFriend = getUser(friendID);

        if (currentFriend != null) {
          friends.add(currentFriend);
        }
      }

    }

    disconnect();

    return friends;

  }

  /**
   * Gets friends with pending status based on ID
   * @param userID
   * @return
   * @throws SQLException
   * @throws IOException
   */
  public ArrayList<User> getPendingFriends(long userID) throws SQLException, IOException {

    ArrayList<User> friends = new ArrayList<>();

    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.GET_PENDING_FRIENDS_QUERY);

    statement.setLong(1, userID);
    statement.setLong(2, userID);

    ResultSet results = statement.executeQuery();

    while (results.next()) {

      long friendID = results.getLong(1) != userID ? results.getLong(1) : results.getLong(2);

      boolean notLastActive = results.getLong(4) == DatabaseDAO.PENDING_FRIEND && results.getLong(3) != userID;

      if (notLastActive) {

        User currentFriend = getUser(friendID);

        if (currentFriend != null) {
          friends.add(currentFriend);
        }
      }

    }

    disconnect();

    return friends;

  }

  /**
   * Checks if 2 users are friends
   * @param userID
   * @param myID
   * @return
   * @throws SQLException
   */
  public long areFriends(long userID, long myID) throws SQLException {

    long friendStatus = -1;

    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.ARE_FRIENDS_QUERY);

    statement.setLong(1, Long.min(userID, myID));
    statement.setLong(2, Long.max(userID, myID));


    ResultSet result = statement.executeQuery();


    while (result.next()) {
      System.out.print("User one id: " + result.getLong(1));
      System.out.print("Useer two id: " + result.getLong(2));
      System.out.print("recent_user id: " + result.getLong(3));
      System.out.print("status: " + result.getLong(4));
      System.out.println();

      friendStatus = result.getLong(4);
    }

    disconnect();

    return friendStatus;
  }

  /**
   * Removes 2 friends
   * @param userID
   * @param myID
   * @return
   * @throws SQLException
   */
  public boolean removeFriend(long userID, long myID) throws SQLException {
    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.DELETE_FRIEND_QUERY);

    statement.setLong(1, Long.min(userID, myID));
    statement.setLong(2, Long.max(userID, myID));

    boolean result = statement.executeUpdate() > 0;

    disconnect();

    return result;

  }

  /**
   * Updates Friend Status
   * @param userID
   * @param myID
   * @param status
   * @return
   * @throws SQLException
   */
  public boolean updateFriendStatus(long userID, long myID, int status) throws SQLException {

    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.UPDATE_FRIEND_QUERY);

    statement.setLong(1, status);
    statement.setLong(2, myID);
    statement.setLong(3, Long.min(userID, myID));
    statement.setLong(4, Long.max(userID, myID));


    boolean result = statement.executeUpdate() > 0;

    disconnect();

    return result;

  }

  /**
   * Adds a new friend
   * @param userID
   * @param myID
   * @return
   * @throws SQLException
   */
  public boolean addFriend(long userID, long myID) throws SQLException {
    connect();

    PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.ADD_FRIEND_QUERY);

    statement.setLong(1, Long.min(userID, myID));
    statement.setLong(2, Long.max(userID, myID));
    statement.setLong(3,myID);
    statement.setLong(4,0);

    boolean result = statement.executeUpdate() > 0;

    disconnect();

    return result;
  }


}
