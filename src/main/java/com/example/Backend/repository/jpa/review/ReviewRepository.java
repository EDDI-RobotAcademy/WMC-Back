package com.example.Backend.repository.jpa.review;

import com.example.Backend.entity.product.Product;
import com.example.Backend.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProduct(Product product);


}
