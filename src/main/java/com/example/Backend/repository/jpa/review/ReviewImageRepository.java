package com.example.Backend.repository.jpa.review;

import com.example.Backend.entity.product.ImageData;
import com.example.Backend.entity.review.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {
    //jpql 문법
    @Query("select i from ReviewImage i join i.review r where r.reviewId = :reviewId")
    List<ReviewImage> findAllImagesByReviewId(Long reviewId);

}
