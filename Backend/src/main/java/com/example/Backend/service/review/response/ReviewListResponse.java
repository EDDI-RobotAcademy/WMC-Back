package com.example.Backend.service.review.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewListResponse {
    private Long reviewId;
    private String title;
    private String wirter;
    private String content;


}
