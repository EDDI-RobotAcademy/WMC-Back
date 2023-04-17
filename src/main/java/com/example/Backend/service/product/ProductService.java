package com.example.Backend.service.product;

import com.example.Backend.entity.product.Product;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.product.response.ProductListResponse;
import com.example.Backend.service.product.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Boolean register(ProductRegisterRequest productRegisterRequest);

    List<ProductListResponse> getAllProducts();

    Boolean delete(Long productId);
    
    ProductResponse getProductById(Long productId);

    List<ProductListResponse> getProductsByCategory(Long categoryId);

    void decreaseProductStock(Long productId, Integer quantity);

    List<Product> getAll(String name);

    List<ProductListResponse> getMostSoldProductList();
//    Page<Product> searchByName(String name, Pageable pageable);
}
