package com.revature.comfybake.User.dtos;

import javax.persistence.Column;

public class ShoppingItemRequest {
    private String shoppingItemId;
    private String bakeId;
    private String image;
    private double price;
    private int quantity;
    private int purchase;
    private String userProfileId;

    public ShoppingItemRequest() {
        super();
    }

    public ShoppingItemRequest(String shoppingItemId, String bakeId, String image, double price, int quantity, int purchase, String userProfileId) {
        this.shoppingItemId = shoppingItemId;
        this.bakeId = bakeId;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.purchase = purchase;
        this.userProfileId = userProfileId;
    }

    public String getShoppingItemId() {
        return shoppingItemId;
    }

    public void setShoppingItemId(String shoppingItemId) {
        this.shoppingItemId = shoppingItemId;
    }

    public String getBakeId() {
        return bakeId;
    }

    public void setBakeId(String bakeId) {
        this.bakeId = bakeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }

    public String getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(String userProfileId) {
        this.userProfileId = userProfileId;
    }
}
