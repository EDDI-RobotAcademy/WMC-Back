package com.example.Backend.service.order.response;

import com.example.Backend.entity.member.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ManagerOrderResponse {
    private Long orderId;
    private String productName;
    private Integer price;
    private Integer quantity;
    private Date orderDate;
    private String memberName;
    private Address address;
    private String phoneNumber;
}
