package com.example.Backend.repository.jpa.review;

import com.example.Backend.entity.notice.NoticeImageData;
import com.example.Backend.entity.product.ImageData;
import com.example.Backend.entity.product.Product;
import com.example.Backend.entity.review.ReviewImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewImageDataRepository extends JpaRepository<ReviewImageData, Long> {

    @Query("select i from ReviewImageData i join i.review r where r.reviewId = :reviewId")
    List<ReviewImageData> findAllImagesByReviewId(Long reviewId);
}
