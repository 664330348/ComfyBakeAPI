package com.revature.comfybake.User.Role;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class UserRole {
    @Id
    @Column(name="user_role_id")
    private String userRoleId;

    @Column(unique = true, nullable = false)
    private String role;

    public UserRole() {
        super();
    }

    public UserRole(String userRoleId, String role) {
        this.userRoleId = userRoleId;
        this.role = role;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleId='" + userRoleId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
