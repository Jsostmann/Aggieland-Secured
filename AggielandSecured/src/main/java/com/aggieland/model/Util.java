package com.aggieland.model;

import java.io.*;

import java.sql.SQLException;

public class Util {

    public static InputStream getDefaultProfilePic() throws SQLException, IOException {
        File image = new File("C:\\Users\\James Ostmann\\COMP-496\\Aggieland-Secured\\AggielandSecured\\src\\main\\webapp\\res\\favicon.png");
        FileInputStream is = new FileInputStream(image);
        System.out.println(image.length());
        return is;
    }
}
