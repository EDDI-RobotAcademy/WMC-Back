package com.example.Backend.service.review.response;

import com.example.Backend.entity.review.ReviewImageData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ReviewResponse {

    final private Long reviewId;
    final private Long productId;
    final private Long memberId;
    final private int rating;
    final private String content;
    final private Date regDate;
    final private List<ReviewImageData> imageDataList;
}
