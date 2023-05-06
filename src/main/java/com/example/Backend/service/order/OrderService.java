package com.example.Backend.service.order;

import com.example.Backend.entity.order.Order;
import com.example.Backend.service.order.request.OrderItemRequest;
import com.example.Backend.service.order.response.ManagerOrderResponse;
import com.example.Backend.service.order.response.MyOrderResponse;

import java.util.List;

public interface  OrderService {

    void saveOrder(Order order);
    void createOrderItem(Order order, OrderItemRequest orderItemRequest);
    void createOrder(Long memberId, List<OrderItemRequest> orderItems);
    boolean isProductEnough(Long productId, Integer quantity);
    List<ManagerOrderResponse> getManagerOrderList();

    List<MyOrderResponse> getMyOrderList(Long memberId);
}
