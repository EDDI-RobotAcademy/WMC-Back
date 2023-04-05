package com.example.Backend.service.review.request;

import com.example.Backend.entity.review.Review;
import com.example.Backend.entity.review.ReviewImage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Data
@ToString
public class ReviewRegisterRequest {


    final private String title;
    final private String writer;
    final private float rating;
    final private String content;
    final private List<String> savedFilePaths;

    public Review toReview() {
        Review review = new Review(title,writer,rating,content);

        for (String path : savedFilePaths) {
            ReviewImage reviewImage = new ReviewImage();
            reviewImage.setReviewImage(path);
            review.addReviewImage(reviewImage);
        }

        return review;
    }

}
