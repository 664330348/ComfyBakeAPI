package com.revature.comfybake.Bake;

import com.revature.comfybake.User.Profile.UserProfile;

import javax.persistence.*;

@Entity
@Table(name="bakes")
public class Bake {

    @Id
    @Column(name="bake_id")
    private String bakeId;

    @Column(name = "bake_name", nullable = false)
    private String bakeName;

    @Column(name = "image_url")
    private String image;

    @Column(name = "described", nullable = false)
    private String described;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "recipe", nullable = false)
    private String recipe;

    @ManyToOne
    @JoinColumn(name="user_profile_id", nullable = false, referencedColumnName = "user_profile_id" )
    private UserProfile userProfile;

    public Bake() {
        super();
    }

    public Bake(String bakeId, String bakeName, String image, String described, double price, int quantity, String recipe, UserProfile userProfile) {
        this.bakeId = bakeId;
        this.bakeName = bakeName;
        this.image = image;
        this.described = described;
        this.price = price;
        this.quantity = quantity;
        this.recipe = recipe;
        this.userProfile = userProfile;
    }

    public String getBakeId() {
        return bakeId;
    }

    public void setBakeId(String bakeId) {
        this.bakeId = bakeId;
    }

    public String getBakeName() {
        return bakeName;
    }

    public void setBakeName(String bakeName) {
        this.bakeName = bakeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribed() {
        return described;
    }

    public void setDescribed(String described) {
        this.described = described;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
