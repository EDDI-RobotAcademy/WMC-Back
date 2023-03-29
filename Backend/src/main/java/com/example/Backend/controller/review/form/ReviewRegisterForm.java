package com.example.Backend.controller.review.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewRegisterForm {

    private String title;
    private String writer;
    private String content;

    public ReviewRegisterRequest toReviewRegisterRequest(){
        return new ReviewRegisterRequest(title, writer,content);

    }

}
