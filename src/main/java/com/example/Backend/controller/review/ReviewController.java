package com.example.Backend.controller.review;


import com.example.Backend.controller.review.form.ReviewRegisterForm;
import com.example.Backend.service.member.MemberService;
import com.example.Backend.service.product.ProductService;
import com.example.Backend.service.review.Request.ReviewRequest;
import com.example.Backend.service.review.ReviewService;
import com.example.Backend.service.review.response.ReviewListResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    final private ReviewService reviewService;
    final private MemberService memberService;
    final private ProductService productService;


    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean reviewRegister(@RequestBody ReviewRegisterForm form) throws IOException {
        log.info("reviewRegister(): " + form);

        List<String> savedFiles = form.getFileNames();

        ReviewRequest reviewRequest = new ReviewRequest(
                form.getProductId(),
                form.getMemberId(),
                form.getRating(),
                form.getContent(),
                savedFiles
        );

        return reviewService.register(reviewRequest);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestBody Long reviewId) {

        return reviewService.delete(reviewId);
    }

    @GetMapping("/list")
    public List<ReviewListResponse> getAllReviews() {

        return reviewService.getAllReviews();
    }

}