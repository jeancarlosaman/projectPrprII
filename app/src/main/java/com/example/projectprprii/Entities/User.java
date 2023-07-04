package com.example.projectprprii.Entities;

public class User {
    //store user data
    private int id;
    private String username;
    private String email;
    private String password;
    private String token;

    private static User authenticatedUser;

    //constructor
    public User(int id, String username, String email) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.token = null;

    }


    //getters and setters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public static void setAuthenticatedUser(User authenticatedUser) {
        User.authenticatedUser = authenticatedUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}