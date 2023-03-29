package com.example.Backend.service.review.request;

import com.example.Backend.entity.review.Review;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class ReviewRegisterRequest {
    final private String title;
    final private String writer;
    final private String content;

    public Review toReview() {
        Review review = new Review(title, writer, content);

    }


}
