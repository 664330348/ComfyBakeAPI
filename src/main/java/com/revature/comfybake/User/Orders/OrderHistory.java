package com.revature.comfybake.User.Orders;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="order_history")
public class OrderHistory {
    @Id
    @Column(name="order_history_id")
    private String orderHistoryId;

    public OrderHistory() {
        super();
    }

    public OrderHistory(String orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public String getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryItem(String orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }
}