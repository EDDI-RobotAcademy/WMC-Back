package com.example.Backend.controller.review;

import com.example.Backend.service.review.ReviewService;
import com.example.Backend.service.review.request.ReviewRegisterRequest;
import com.example.Backend.controller.review.form.ReviewRegisterForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ReviewController {

    final private ReviewService reviewService;

    @PostMapping(value = "/register")
    public boolean ReviewRegister(@ModelAttribute ReviewRegisterForm form) throws IOException {
        log.info("ReviewRegister(): " + form);

        ReviewRegisterRequest request = new ReviewRegisterRequest(form.getTitle(), form.getWriter(), form.getContent());

        return reviewService.register(request);
    }
}