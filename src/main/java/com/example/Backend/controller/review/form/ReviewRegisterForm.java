package com.example.Backend.controller.review.form;

import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.review.request.ReviewRegisterRequest;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@ToString
@NoArgsConstructor
public class ReviewRegisterForm {

    private String title;
    private String writer;
    private float rating;
    private String content;
    private List<MultipartFile> fileList;

    //request로 바꾸는 메서드
    public ReviewRegisterRequest toReviewRegisterRequest(List<String> savedFilePaths) {
        return new ReviewRegisterRequest(title, writer,rating, content, savedFilePaths);
    }
}