package com.example.Backend.service.review;

import com.example.Backend.service.review.request.ReviewRegisterRequest;
import com.example.Backend.service.review.response.ReviewListResponse;

import java.util.List;

public interface ReviewService {
    Boolean register(ReviewRegisterRequest reviewRegisterRequest);

    List<ReviewListResponse> getAllReviews();
}