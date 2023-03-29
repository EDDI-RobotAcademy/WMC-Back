package com.example.Backend.service.review;

import com.example.Backend.entity.review.Review;
import com.example.Backend.repository.review.ReviewRepository;
import com.example.Backend.service.review.response.ReviewListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    final private ReviewRepository reviewRepository;

    @Override
    public List<ReviewListResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewListResponse> reviewListResponses = new ArrayList<>();

        for (Review review : reviews) {
            ReviewListResponse response = new ReviewListResponse(
                    review.getReviewId(),
                    review.getTitle(),
                    review.getWriter(),
                    review.getContent()
            );
            reviewListResponses.add(response);
        }

        return reviewListResponses;
    }
}