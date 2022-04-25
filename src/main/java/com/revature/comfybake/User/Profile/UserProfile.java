package com.revature.comfybake.User.Profile;

import com.revature.comfybake.User.User;

import javax.persistence.*;

@Entity
@Table(name="user_profiles")
public class UserProfile {
    @Id
    @Column(name="user_profile_id")
    private String userProfileId;

    @Column(name="first_name", nullable = false)
    private String FirstName;

    @Column(name="last_name", nullable = false)
    private String LastName;

    @Column(name="email")
    private String Email;

    public UserProfile() {
        super();
    }

    public UserProfile(String userProfileId, String firstName, String lastName, String email) {
        this.userProfileId = userProfileId;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
    }

    public String getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(String userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
