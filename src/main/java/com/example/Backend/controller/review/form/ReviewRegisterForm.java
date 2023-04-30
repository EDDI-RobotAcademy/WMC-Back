package com.example.Backend.controller.review.form;

import lombok.*;


import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class ReviewRegisterForm {

    private Long productId;
    private String token;
    private int rating;
    private String content;
    private List<String> fileNames;

}
