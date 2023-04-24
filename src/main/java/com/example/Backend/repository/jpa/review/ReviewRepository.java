package com.example.Backend.repository.jpa.review;

import com.example.Backend.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
