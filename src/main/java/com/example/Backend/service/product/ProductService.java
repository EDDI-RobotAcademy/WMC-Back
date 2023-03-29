package com.example.Backend.service.product;

import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.product.response.ProductListResponse;

import java.util.List;

public interface ProductService {
    Boolean register(ProductRegisterRequest productRegisterRequest);

    List<ProductListResponse> getAllProducts();
}
