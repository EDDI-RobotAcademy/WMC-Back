package com.example.Backend.controller.review.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewRegisterForm {

    private Long productId;
    private Long memberId;
    private int rating;
    private String content;
    private List<String>fileNames;


}
