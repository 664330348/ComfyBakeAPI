package com.revature.comfybake.User.Orders;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="order_history")
public class OrderHistory {
    @Id
    @Column(name="order_history")
    private String orderHistoryItem;

    public OrderHistory() {
        super();
    }

    public OrderHistory(String orderHistoryItem) {
        this.orderHistoryItem = orderHistoryItem;
    }

    public String getOrderHistoryItem() {
        return orderHistoryItem;
    }

    public void setOrderHistoryItem(String orderHistoryItem) {
        this.orderHistoryItem = orderHistoryItem;
    }
}