package com.example.Backend.service.product.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductListResponse {
    private Long productId;
    private String name;
    private String description;
    private Integer stock;
    private Integer price;
    private String firstPhoto;
}
