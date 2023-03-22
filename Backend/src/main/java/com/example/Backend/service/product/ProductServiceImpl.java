package com.example.Backend.service.product;

import com.example.Backend.entity.product.Product;
import com.example.Backend.repository.product.ProductRepository;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;


    @Override

    public Boolean register(ProductRegisterRequest productRegisterRequest) {
        final Product product = productRegisterRequest.toProduct();
        productRepository.save(product);

        return true;
    }
}
