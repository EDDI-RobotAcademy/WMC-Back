package com.example.Backend.repository.jpa.product;

import com.example.Backend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.category c WHERE c.categoryId = :categoryId")
    List<Product> findByCategoryCategoryId(Long categoryId);
}
