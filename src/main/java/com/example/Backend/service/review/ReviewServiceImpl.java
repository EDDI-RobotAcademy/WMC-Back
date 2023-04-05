package com.example.Backend.service.review;

import com.example.Backend.entity.product.Product;
import com.example.Backend.entity.review.Review;
import com.example.Backend.repository.review.ReviewImageRepository;
import com.example.Backend.repository.review.ReviewRepository;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.review.request.ReviewRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    final private ReviewRepository reviewRepository;

    final private ReviewImageRepository reviewImageRepository;

    @Override
    public Boolean register(ReviewRegisterRequest reviewRegisterRequest) {
        final Review review = reviewRegisterRequest.toReview();
        reviewRepository.save(review);

        return true;
    }


}
