package com.example.Backend.service.product.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class ProductSaleResponse {
    final private Long productId;
    final private String name;
    final private Integer price;
    final private Integer stock;
    final private Date date;
}
