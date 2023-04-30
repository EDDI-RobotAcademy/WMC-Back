package com.example.Backend.service.review.Request;

import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.product.Product;
import com.example.Backend.entity.review.Review;
import com.example.Backend.entity.review.ReviewImageData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class ReviewRequest {

    private Long productId;
    private Long memberId;
    private int rating;
    private String content;
    private List<String> savedFilePaths;

    public Review toReview(Product product, Member member) {

        Review review = new Review(product,member,rating,content);

        if (savedFilePaths != null) {
            for (String path : savedFilePaths) {
                ReviewImageData reviewImageData = new ReviewImageData();
                reviewImageData.setReviewImageData(path);
                review.addReviewImageData(reviewImageData);
            }
        }

        return review;
    }


}
