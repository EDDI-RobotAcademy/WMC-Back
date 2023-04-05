package com.example.Backend.service.review;

import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.review.request.ReviewRegisterRequest;

public interface ReviewService {
    Boolean register(ReviewRegisterRequest reviewRegisterRequest);
}
