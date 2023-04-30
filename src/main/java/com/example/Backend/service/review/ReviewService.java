package com.example.Backend.service.review;


import com.example.Backend.service.review.Request.ReviewRequest;
import com.example.Backend.service.review.response.ReviewListResponse;
import com.example.Backend.service.review.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

    Boolean register(ReviewRequest reviewRequest);

    List<ReviewListResponse> getAllReviews();

    Boolean delete(Long reviewId);

    ReviewResponse getReviewById(Long reviewId);


}
