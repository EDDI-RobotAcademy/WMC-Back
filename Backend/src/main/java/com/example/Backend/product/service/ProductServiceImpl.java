package com.example.Backend.product.service;

import com.example.Backend.product.controller.request.ProductRequest;
import com.example.Backend.product.entity.Product;
import com.example.Backend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;

    @Override
    public void register(ProductRequest productRequest) {
        Product product = new Product();

        product.setProductName(productRequest.getProductName());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);
    }
}
