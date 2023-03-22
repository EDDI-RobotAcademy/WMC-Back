package com.example.Backend;

import com.example.Backend.entity.product.ImageData;
import com.example.Backend.service.product.ProductService;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductService productService;

    @Test
    public void 상품등록_확인() {
        assertTrue(productService.register(new ProductRegisterRequest(
                "test", "test입니다", 100, 1000,new ArrayList<ImageData>())));
    }
}