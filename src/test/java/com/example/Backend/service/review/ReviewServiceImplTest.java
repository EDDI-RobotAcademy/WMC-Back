package com.example.Backend.service.review;

import com.example.Backend.repository.jpa.review.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceImplTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void register() {
        reviewRepository.deleteAll();
        assertTrue(reviewRepository.count() == 0);
    }
}