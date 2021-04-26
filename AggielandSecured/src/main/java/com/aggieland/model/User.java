package com.aggieland.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;


public class User {

    private long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String profilePictureBase64;
    private String userInfo;
    private Date dateCreated;
    private String major;
    private String classification;

    public User() {
        this.userInfo = "Nothing Yet";
        this.profilePictureBase64 = "/AggielandSecured/res/favicon.png";
    }

    public long getUserId() {

        return this.userId;
    }

    public String getUserName() {

        return userName;
    }

    public String getEmail() {

        return email;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getProfilePictureBase64() {

        return profilePictureBase64;
    }

    public String getUserInfo() {

        return this.userInfo;
    }

    public String getDateCreated() {
        SimpleDateFormat monthDayformatter = new SimpleDateFormat("MMMMM dd, yyyy");
        return monthDayformatter.format(dateCreated);
    }

    public Date getDateCreatedAsDate() {

      return this.dateCreated;
    }

    public String getMajor() {

        return this.major;
    }

    public String getClassification() {

        return classification;

    }

    public String getFullName() {

      return this.firstName + " " + this.lastName;
    }

    public void setMajor(String major) {

        this.major = major;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public void setProfilePictureBase64(String base64Image) {

        this.profilePictureBase64 = base64Image;
    }

    public void setUserInfo(String userInfo) {

        this.userInfo = userInfo;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public void setDateCreated(Date dateCreated) {

        this.dateCreated = dateCreated;
    }

    public void setClasssification(String classification) {

        this.classification = classification;
    }

    private static String convertImage(InputStream inputStream) throws IOException {
        String result;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = outputStream.toByteArray();

        result = Base64.getEncoder().encodeToString(imageBytes);

        inputStream.close();
        outputStream.close();

        return result;

    }


    public static User createUser(ResultSet result) throws SQLException, IOException {

        User newUser = new User();

        newUser.setUserId(result.getInt(1));
        newUser.setFirstName(result.getString(2));
        newUser.setLastName(result.getString(3));
        newUser.setUserName(result.getString(4));
        newUser.setEmail(result.getString(5));
        newUser.setDateCreated(result.getDate(8));

        if(result.getBlob(9) != null) {
            newUser.setProfilePictureBase64(result.getString(9));
        }

        if(result.getString(10) != null) {
            newUser.setUserInfo(result.getString(10));
        }

        newUser.setMajor(result.getString(11));

        return newUser;
    }

    public static User createUserFromSearch(ResultSet result) throws SQLException {

        User newUser = new User();

        newUser.setUserId(result.getLong(1));
        System.out.println("Result col 2: " + result.getString(2));
        newUser.setUserName(result.getString(2));
        newUser.setFirstName(result.getString(3));
        newUser.setLastName(result.getString(4));
        newUser.setEmail(result.getString(5));
        newUser.setMajor(result.getString(6));

        return  newUser;

    }


    public static User createUser(HttpServletRequest request) {
        User newUser = new User();

        newUser.setFirstName(request.getParameter("firstName"));
        newUser.setLastName(request.getParameter("lastName"));
        newUser.setUserName(request.getParameter("userName"));
        newUser.setEmail(request.getParameter("email"));

        // get current formatted date
        Date date = Date.valueOf(LocalDate.now());
        newUser.setDateCreated(date);

        newUser.setMajor(request.getParameter("major"));

        return newUser;

    }

    public static void updateUser(HttpServletRequest request) throws IOException, ServletException {

        User currentUser = (User)request.getSession(false).getAttribute("user");

        InputStream inputStream;

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Part filePart = request.getPart("profilePicture");
        String userInfo = request.getParameter("userInfo");
        String major = request.getParameter("major");

        if(!firstName.isEmpty()) {
            currentUser.setFirstName(firstName);
        }
        if(!lastName.isEmpty()) {
            currentUser.setLastName(lastName);
        }
        if(!email.isEmpty()) {
            currentUser.setEmail(email);
        }
        if(!password.isEmpty()) {
            // password update
        }
        if(!userInfo.isEmpty()) {
            currentUser.setUserInfo(userInfo);
        }
        if(!major.isEmpty()) {
            currentUser.setMajor(major);
        }

        if(filePart != null && filePart.getSize() > 0) {
            inputStream = filePart.getInputStream();
            String image = convertImage(inputStream);
            currentUser.setProfilePictureBase64("data:image/jpg;base64," + image);
        }

    }

    @Override
    public String toString() {
      return  "User Id: " + this.userId + System.lineSeparator() +
              "First Name: " + this.firstName + System.lineSeparator() +
              "Last Name:  " + this.lastName + System.lineSeparator() +
              "User Name: " + this.userName + System.lineSeparator() +
              "Email: " + this.email + System.lineSeparator() +
              "Major: " + this.major + System.lineSeparator() +
              "Date Created: "  + this.getDateCreated() + System.lineSeparator() +
              "Profile Picture: " + this.profilePictureBase64 + System.lineSeparator() +
              "User Info: " + this.userInfo + System.lineSeparator();
    }

}
