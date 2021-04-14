package com.aggieland.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BasicDAO {

    protected String databaseConnectionURL;
    protected String databaseUsername;
    protected String databasePassword;

    private Connection databaseConnection;

    public BasicDAO(String databaseConnectionURL, String databaseUsername, String databasePassword) {

        this.databaseConnectionURL = databaseConnectionURL;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
    }

    protected Connection getDatabaseConnection() {

        return databaseConnection;
    }

    protected void connect() throws SQLException {
        if(databaseConnection == null || databaseConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            databaseConnection = DriverManager.getConnection(databaseConnectionURL,databaseUsername,databasePassword);
        }
    }

    protected void disconnect() throws SQLException {

        if(databaseConnection != null && !databaseConnection.isClosed()) {
            databaseConnection.close();
        }
    }

}
