package com.revature.comfybake.User.Orders;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @Column(name="order_item_id")
    private String orderItemId;

    @Column (name = "item_name")
    private String itemName;

    @Column (name = "item_image")
    private String itemImage;

    @Column (name = "item_price")
    private double itemPrice;

    @Column (name = "quantity")
    private int quantity;

    @Column (name = "total_cost")
    private double totalCost;

    @Column (name = "completed_time")
    private LocalDateTime completedTime;

    @ManyToOne
    @JoinColumn(name = "order_history_id", nullable = false)
    private OrderHistory orderHistory;

    @Column (name = "group_id")
    private String groupId;

    public OrderItem() {
        super();
    }

    public OrderItem(String orderItemId, String itemName, String itemImage, double itemPrice, int quantity, double totalCost, LocalDateTime completedTime, OrderHistory orderHistory, String groupId) {
        this.orderItemId = orderItemId;
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.totalCost = totalCost;
        this.completedTime = completedTime;
        this.orderHistory = orderHistory;
        this.groupId = groupId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
    }

    public OrderHistory getOrderHistoryId() {
        return orderHistory;
    }

    public void setOrderHistoryId(OrderHistory orderHistoryId) {
        this.orderHistory = orderHistoryId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
