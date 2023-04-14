package com.example.Backend.service.order.request;

import java.util.List;

public class KakaoPayRequest {

    private List<OrderItemRequest> orderItems;

    private String token;

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
