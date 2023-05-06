package com.example.Backend.service.review.response;

import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.product.Category;
import com.example.Backend.entity.product.Product;
import com.example.Backend.entity.review.Review;
import com.example.Backend.entity.review.ReviewImageData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
public class ReviewListResponse {
    final private Long reviewId;
    final private String productName;
    final private String productCategory;
    final private String username;
    final private int rating;
    final private String content;
    final private Date regDate;
    private String firstPhoto;

    public ReviewListResponse(Review review) {
        this.reviewId = review.getReviewId();
        this.productName = review.getProduct().getName();
        this.productCategory = review.getProduct().getCategory().getName();
        this.username = review.getMember().getUsername();
        this.rating = review.getRating();
        this.content = review.getContent();
        this.regDate = review.getRegDate();
    }
}
