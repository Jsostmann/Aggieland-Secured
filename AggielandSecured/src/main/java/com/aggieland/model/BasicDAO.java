package com.aggieland.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is the basis class for our DAO Classes it gives the child classes access to the Database
 */
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

    /**
     * This connects to our database if it exists or is closed
     * @throws SQLException
     */
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

    /**
     * This disconnects us from the database if it exists
     * @throws SQLException
     */
    protected void disconnect() throws SQLException {

        if(databaseConnection != null && !databaseConnection.isClosed()) {
            databaseConnection.close();
        }
    }

}
