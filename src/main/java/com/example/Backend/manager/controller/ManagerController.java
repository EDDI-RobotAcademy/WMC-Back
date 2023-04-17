package com.example.Backend.manager.controller;

import com.example.Backend.entity.product.Product;
import com.example.Backend.service.product.ProductService;
import com.example.Backend.service.product.response.ProductListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ManagerController {

    final private ProductService productService;

    @GetMapping("/sales-data")
    public List<ProductListResponse> getSalesData() {
        log.info("getSalesData()");

        List<ProductListResponse> productList = productService.getMostSoldProductList(PageRequest.of(0, 100));

        return productList;
    }
}
