package com.revature.comfybake.Bake.dtos;

import javax.persistence.Column;

public class NewBakeRequest {
    private String bakeId;
    private String bakeName;
    private String image;
    private String described;
    private double price;
    private int quantity;
    private String recipe;

    public NewBakeRequest() {
        super();
    }

    public NewBakeRequest(String bakeId, String bakeName, String image, String described, double price, int quantity, String recipe) {
        this.bakeId = bakeId;
        this.bakeName = bakeName;
        this.image = image;
        this.described = described;
        this.price = price;
        this.quantity = quantity;
        this.recipe = recipe;
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

    @Override
    public String toString() {
        return "NewBakeRequest{" +
                "bakeId='" + bakeId + '\'' +
                ", bakeName='" + bakeName + '\'' +
                ", image='" + image + '\'' +
                ", described='" + described + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
