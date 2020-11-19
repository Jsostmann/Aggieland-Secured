package com.aggieland.database;

import com.aggieland.model.User;
import com.aggieland.websocket.ChatroomSocket;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DatabaseDAO {

    private static final Logger LOG = Logger.getLogger(DatabaseDAO.class.getName());
    private ArrayList<User> connectedUsers;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/aggieland";
    private static final String DB_USERNAME ="root";
    private static final String DB_PASSWORD = "root";
    private static Connection databaseConnection = null;
    private static Statement statement = null;
    private static ResultSet queryResult = null;

    //String query ="select * from USER";

    public DatabaseDAO() {

        try{

            Class.forName("com.mysql.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            statement = databaseConnection.createStatement();

            result = statement.executeQuery(query);

            while(result.next()) {

                for (int i = 0; i < 6; i++) {

                    System.out.print(result.getString(i + 1) + " | ");

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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    private static synchronized void disconnect() {

    }


}
