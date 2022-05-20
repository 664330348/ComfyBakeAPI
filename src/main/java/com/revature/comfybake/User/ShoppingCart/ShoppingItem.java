package com.revature.comfybake.User.ShoppingCart;

import javax.persistence.*;

@Entity
@Table(name="shopping_items")
public class ShoppingItem {
    @Id
    @Column(name="shopping_item_id")
    private String shoppingItemId;

    @Column(name="bakeId")
    private String bakeId;

    @Column(name="image")
    private String image;

    @Column(name="price")
    private double price;

    @Column(name="quantity")
    private int quantity;

    @Column(name="purchase")
    private int purchase;

    @Column(name="user_profile_id")
    private String userProfileId;

    @ManyToOne
    @JoinColumn(name="shopping_list_id", nullable = false)
    private ShoppingList shoppingList;

    public ShoppingItem() {
        super();
    }

    public ShoppingItem(String shoppingItemId, String bakeId, String image, double price, int quantity, int purchase, String userProfileId, ShoppingList shoppingList) {
        this.shoppingItemId = shoppingItemId;
        this.bakeId = bakeId;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.purchase = purchase;
        this.userProfileId = userProfileId;
        this.shoppingList = shoppingList;
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

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }
}
