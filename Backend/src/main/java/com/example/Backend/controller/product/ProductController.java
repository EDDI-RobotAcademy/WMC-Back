package com.example.Backend.controller.product;

import com.example.Backend.controller.product.form.ProductRegisterForm;
import com.example.Backend.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductController {

    final private ProductService productService;

    @PostMapping("/register")
    public boolean productRegister(@RequestBody ProductRegisterForm form) {
        log.info("productRegister(): " + form);

        return productService.register(form.toProductRegisterRequest());
    }
}
