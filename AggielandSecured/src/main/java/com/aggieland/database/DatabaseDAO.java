package com.aggieland.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseDAO {

    String databaseUrl = "jdbc:mysql://localhost:3306/aggieland";
    String username ="root";
    String password = "Stuie111";

    Connection databaseConnection = null;
    Statement statement = null;
    ResultSet result = null;

    String query ="select * from USER";

    public DatabaseDAO() {

        try{

            Class.forName("com.mysql.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(databaseUrl,username,password);
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



}
