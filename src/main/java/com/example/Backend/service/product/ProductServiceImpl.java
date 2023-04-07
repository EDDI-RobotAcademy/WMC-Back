package com.example.Backend.service.product;

import com.example.Backend.entity.product.Category;
import com.example.Backend.entity.product.ImageData;
import com.example.Backend.entity.product.Product;
import com.example.Backend.repository.product.ImageDataRepository;
import com.example.Backend.repository.product.ProductRepository;
import com.example.Backend.service.category.CategoryService;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.product.response.ProductListResponse;
import com.example.Backend.service.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;

    final private ImageDataRepository imageDataRepository;

    final private CategoryService categoryService;

    @Override
    public Boolean register(ProductRegisterRequest productRegisterRequest) {
        final Product product = productRegisterRequest.toProduct();
        productRepository.save(product);

        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long productId) {
        Optional<Product> maybeProduct = productRepository.findById(productId);
        if (maybeProduct.isPresent()) {
            Product product = maybeProduct.get();
            imageDataRepository.deleteAll(product.getImageDataList());
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Optional<Product> maybeProduct = productRepository.findById(productId);
        if (maybeProduct.isPresent()) {
            Product product = maybeProduct.get();
            List<ImageData> imageDataList = imageDataRepository.findAllImagesByProductId(productId);
            return new ProductResponse(
                    product.getProductId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    imageDataList
            );
        } else {
            return null;
        }

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

    @Override
    @Transactional
    public List<ProductListResponse> getProductsByCategory(Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        List<Product> products = category.getProductList();
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
