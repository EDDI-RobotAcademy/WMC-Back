package com.example.Backend.service.order.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private Integer quantity;
}
