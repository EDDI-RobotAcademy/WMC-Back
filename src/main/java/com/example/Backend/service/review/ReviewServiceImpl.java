package com.example.Backend.service.review;

import com.example.Backend.entity.review.Review;
import com.example.Backend.repository.jpa.review.ReviewImageRepository;
import com.example.Backend.repository.jpa.review.ReviewRepository;
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
