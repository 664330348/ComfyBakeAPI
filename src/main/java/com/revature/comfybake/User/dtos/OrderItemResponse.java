package com.revature.comfybake.User.dtos;

import com.revature.comfybake.User.Orders.OrderItem;


public class OrderItemResponse {
    private String itemName;
    private String itemImage;
    private double itemPrice;
    private int quantity;

    public OrderItemResponse() {
        super();
    }

    public OrderItemResponse(String itemName, String itemImage, double itemPrice, int quantity) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemResponse{" +
                "itemName='" + itemName + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", itemPrice=" + itemPrice +
                ", quantity=" + quantity +
                '}';
    }
}
