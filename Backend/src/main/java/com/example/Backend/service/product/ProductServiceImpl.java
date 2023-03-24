package com.example.Backend.service.product;

import com.example.Backend.entity.product.ImageData;
import com.example.Backend.entity.product.Product;
import com.example.Backend.repository.product.ImageDataRepository;
import com.example.Backend.repository.product.ProductRepository;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.product.response.ProductListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;

    final private ImageDataRepository imageDataRepository;

    @Override
    public Boolean register(ProductRegisterRequest productRegisterRequest) {
        final Product product = productRegisterRequest.toProduct();
        productRepository.save(product);

        return true;
    }

    @Override
    public List<ProductListResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductListResponse> productListResponses = new ArrayList<>();

        for (Product product : products) {
            String firstPhoto = null;
            List<ImageData> images = imageDataRepository.findAllImagesByProductId(product.getProductId());
            if (!images.isEmpty()) {
                firstPhoto = images.get(0).getImageData();
            }

            ProductListResponse response = new ProductListResponse(
                    product.getProductId(),
                    product.getName(),
                    product.getDescription(),
                    product.getStock(),
                    product.getPrice(),
                    firstPhoto
            );
            productListResponses.add(response);
        }

        return productListResponses;
    }

}
