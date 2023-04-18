package com.example.Backend.service.order;

import com.example.Backend.entity.order.Order;
import com.example.Backend.service.order.request.OrderItemRequest;

import java.util.List;

public interface  OrderService {

    void saveOrder(Order order);
    void createOrderItem(Order order, OrderItemRequest orderItemRequest);
    void createOrder(Long memberId, List<OrderItemRequest> orderItems);
    boolean isProductEnough(Long productId, Integer quantity);
}
