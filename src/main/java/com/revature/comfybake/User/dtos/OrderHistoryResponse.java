package com.revature.comfybake.User.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderHistoryResponse {
    private double totalCost;
    private LocalDateTime completedTime;
    private ArrayList<OrderItemResponse> orderItemResponses;

    public OrderHistoryResponse() {
        orderItemResponses = new ArrayList<>();
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

    public ArrayList<OrderItemResponse> getOrderItemResponses() {
        return orderItemResponses;
    }

    public void setOrderItemResponses(ArrayList<OrderItemResponse> orderItemResponses) {
        this.orderItemResponses = orderItemResponses;
    }

    public void addOrderItemResponses(OrderItemResponse orderItemResponse){
        this.orderItemResponses.add(orderItemResponse);
    }

}
