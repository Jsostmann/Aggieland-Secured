package com.aggieland.model;

import javax.servlet.http.HttpServletRequest;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String profilePictureBase64;
    private String userInfo;

    public User(String firstName, String lastName, String userName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
        this(null,null,null,null);
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static User createUser(HttpServletRequest request) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User(firstName,lastName,userName,email);

        return user;
    }

}
