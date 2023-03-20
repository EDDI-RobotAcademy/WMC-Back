package com.example.Backend.product.controller;


import com.example.Backend.product.controller.request.ProductRequest;
import com.example.Backend.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductController {

    final private ProductService productService;

    @PostMapping("/register")
    public void productRegister(@RequestBody ProductRequest productRequest) {
        log.info("productRegister()");

        productService.register(productRequest);
    }
}
