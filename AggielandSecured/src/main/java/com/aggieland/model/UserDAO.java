package com.aggieland.model;

import com.aggieland.auth.AuthoizationUtil;
import com.aggieland.database.DatabaseDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.sql.*;

public class UserDAO extends BasicDAO{

    public UserDAO(String databaseConnectionURL, String databaseUsername, String databasePassword) {
        super(databaseConnectionURL,databaseUsername,databasePassword);
    }

    public boolean verifiedUser(String userName, String password) throws SQLException  {

        connect();

        PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.FIND_USER_QUERY);

        statement.setString(1, userName);

        ResultSet result = statement.executeQuery();

        if(result.next()) {

        }
        boolean verified = result.next();
        System.out.println("verified: " + verified);
        disconnect();

        return  verified;
    }

    public boolean userExists(String userName) throws SQLException {
        connect();

        PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.FIND_USER_QUERY);

        statement.setString(1, userName);

        ResultSet result = statement.executeQuery();

        boolean userFound = result.next();

        System.out.println("user exists: " + userFound);
        disconnect();

        return userFound;
    }

    public User getUser(String userName) throws SQLException {

        User foundUser = null;

        connect();

        PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.FIND_USER_QUERY);

        statement.setString(1, userName);

        ResultSet result = statement.executeQuery();

        if(result.next()) {
            foundUser = new User();
            foundUser.setFirstName(result.getString(2));
            foundUser.setLastName(result.getString(3));
            foundUser.setEmail(result.getString(4));
            foundUser.setUserName(userName);
        }

        disconnect();

        return foundUser;
    }

    public boolean addUser(User user, String password) throws SQLException, IOException {

            connect();

            PreparedStatement statement = getDatabaseConnection().prepareStatement(DatabaseDAO.ADD_USER_QUERY);

            String salt = AuthoizationUtil.generateSalt();
            StringBuilder saltedPassword = new StringBuilder(50);
            saltedPassword.append(password);
            saltedPassword.append(salt);

            System.out.println(saltedPassword);

            String hashedPassword = AuthoizationUtil.hashSaltedPassword(saltedPassword.toString(),salt);

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            InputStream a = Util.getDefaultProfilePic();

            statement.setString(1,user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3,user.getUserName());
            statement.setString(4,hashedPassword);
            statement.setString(5,salt);
            statement.setString(6,user.getEmail());
            //statement.setDate(7,sqlDate);
            statement.setBinaryStream(7,a,57905);
            statement.setNull(8,Types.NULL);

            boolean userAdded = statement.executeUpdate() > 0;

            disconnect();

            return userAdded;
    }
}
