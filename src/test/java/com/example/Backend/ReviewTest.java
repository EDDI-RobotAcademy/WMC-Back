package com.example.Backend;

import com.example.Backend.repository.review.ReviewRepository;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.review.ReviewService;
import com.example.Backend.service.review.request.ReviewRegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void 리뷰등록_테스트(){
        reviewRepository.deleteAll();
        assertTrue(reviewRepository.count() == 0);
        List<String> savedFilePaths = Arrays.asList(
                "/path/to/test/image1.jpg",
                "/path/to/test/image2.jpg",
                "/path/to/test/image3.jpg"
        );
        assertTrue(reviewService.register(new ReviewRegisterRequest("asdf","asdf",3.5f, "test입니다", savedFilePaths)));
        assertTrue(reviewRepository.count() == 1);
    }
}
