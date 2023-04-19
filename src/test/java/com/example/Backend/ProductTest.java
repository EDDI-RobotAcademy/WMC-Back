package com.example.Backend;

import com.example.Backend.entity.product.Category;
import com.example.Backend.entity.product.ImageData;
import com.example.Backend.entity.product.Product;
import com.example.Backend.repository.product.ProductRepository;
import com.example.Backend.service.category.CategoryService;
import com.example.Backend.service.product.ProductService;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.product.response.ProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void 상품등록_확인() {
        Category newCategory = categoryService.createCategory("testCategory");

        List<String> savedFilePaths = Arrays.asList(
                "src/assets/productImages/carin1.png",
                "src/assets/productImages/carin2.png",
                "src/assets/productImages/carin3.png"
        );

        assertTrue(productService.register(new ProductRegisterRequest(
                "test", "test입니다", 100, 1000, newCategory, savedFilePaths)));
    }

    @Test
    public void 상품삭제_확인() {
        productRepository.deleteAll();
        assertEquals(0, productRepository.count());

        Category newCategory = categoryService.createCategory("testCategory");

        List<String> savedFilePaths = Arrays.asList(
                "src/assets/productImages/carin1.png",
                "src/assets/productImages/carin2.png",
                "src/assets/productImages/carin3.png"
        );

        assertTrue(productService.register(new ProductRegisterRequest(
                "test", "test입니다", 100, 1000, newCategory, savedFilePaths)));
        assertEquals(1, productRepository.count());

        List<Product> products = productRepository.findAll();
        Product product = products.get(0);
        assertTrue(productService.delete(product.getProductId()));
    }

    @Test
    public void 상품상세정보() {
        productRepository.deleteAll();
        assertEquals(0, productRepository.count());

        Category newCategory = categoryService.createCategory("testCategory");

        List<String> savedFilePaths = Arrays.asList(
                "src/assets/productImages/carin1.png",
                "src/assets/productImages/carin2.png",
                "src/assets/productImages/carin3.png"
        );

        assertTrue(productService.register(new ProductRegisterRequest(
                "test", "test입니다", 100, 1000, newCategory, savedFilePaths)));
        assertEquals(1, productRepository.count());

        List<Product> products = productRepository.findAll();
        Product product = products.get(0);
        ProductResponse productResponse = productService.getProductById(product.getProductId());
        //assertEquals(product.getProductId(), productResponse.getId());
    }
}
