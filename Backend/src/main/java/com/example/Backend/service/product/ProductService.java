package com.example.Backend.service.product;

import com.example.Backend.service.product.request.ProductRegisterRequest;

public interface ProductService {
    Boolean register(ProductRegisterRequest productRegisterRequest);
}
