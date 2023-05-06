package com.example.Backend.service.order.response;

import com.example.Backend.entity.member.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MyOrderResponse {
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer price;
    private Integer quantity;
    private Date orderDate;
    private Address address;
    private String photo;
}
