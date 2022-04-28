package com.revature.comfybake.User;

import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Role.UserRole;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="username", nullable = false)
    private String Username;

    @Column(name="password", nullable = false)
    private String Password;

    @ManyToOne
    @JoinColumn(name="user_role_id", nullable = false)
    private UserRole userRole;

    @OneToOne
    @JoinColumn(name="user_profile_id", nullable = false, unique = true, referencedColumnName = "user_profile_id" )
    private UserProfile userProfile;

    public User() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
