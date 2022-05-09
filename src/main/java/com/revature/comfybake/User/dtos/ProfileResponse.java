package com.revature.comfybake.User.dtos;

public class ProfileResponse {
    private String firstname;
    private String lastname;
    private String email;
    private String photo;

    public ProfileResponse() {
        super();
    }

    public ProfileResponse(String firstname, String lastname, String email, String photo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.photo = photo;
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
