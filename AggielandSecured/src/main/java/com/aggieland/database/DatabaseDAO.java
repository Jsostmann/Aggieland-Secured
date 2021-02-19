package com.aggieland.database;

import com.aggieland.model.User;
import com.aggieland.websocket.ChatroomSocket;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DatabaseDAO {

    private static final Logger LOG = Logger.getLogger(DatabaseDAO.class.getName());
    private ArrayList<User> connectedUsers;
    private static final String DB_URL = "jdbc:mysql://localhost:8002/aggielandsql";
    private static final String DB_USERNAME ="root";
    private static final String DB_PASSWORD = "root";
    private static Connection databaseConnection = null;
    private static Statement statement = null;
    private static ResultSet queryResult = null;
    private static String query = null;

    public static final String GET_USER_QUERY = "SELECT * FROM USER WHERE user_name = ? and pass_word = ?";
    public static final String ADD_USER_QUERY = "INSERT INTO USER (first_name, last_name, email, user_name, pass_word, online_status) values(?, ?, ?, ?, ?, ?)";
    public static final String FIND_USER_QUERY = "SELECT * FROM USER WHERE user_name = ?";

    public DatabaseDAO() {

        try{

            //Class.forName("com.mysql.jdbc.Driver");
            //databaseConnection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            //statement = databaseConnection.createStatement();

            queryResult = statement.executeQuery(query);

            while(queryResult.next()) {

                for (int i = 0; i < 6; i++) {

                    System.out.print(queryResult.getString(i + 1) + " | ");

                }
                System.out.println();
            }

            databaseConnection.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }
    private static synchronized void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            statement = databaseConnection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void disconnect() {

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


}
