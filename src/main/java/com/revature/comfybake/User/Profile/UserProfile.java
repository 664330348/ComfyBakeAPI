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
    private String firstname;

    @Column(name="last_name", nullable = false)
    private String lastname;

    @Column(name="email")
    private String email;

    @Column(name="photo")
    private String photo;

    public UserProfile() {
        super();
    }

    public UserProfile(String userProfileId, String firstname, String lastname, String email, String photo) {
        this.userProfileId = userProfileId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.photo = photo;
    }

    public String getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(String userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
