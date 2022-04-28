package com.revature.comfybake;

import com.revature.comfybake.User.User;

public class Principal {
    private String userId;
    private String username;
    private String role;

    public Principal() {
    }

    public Principal(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.role = user.getUserRole().getRole();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "userId='" + userId + '\'' +
                ", userName='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
