package com.bosgii.internshipmanagement.dto;

public class UserDTO {
    private String id;
    private String password;

    public String getUsername() {
        return id;
    }

    public void setUsername(String username) {
        this.id = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
