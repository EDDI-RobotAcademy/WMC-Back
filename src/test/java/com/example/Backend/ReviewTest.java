package com.example.Backend;

import com.example.Backend.entity.product.Category;
import com.example.Backend.repository.jpa.product.ProductRepository;
import com.example.Backend.repository.jpa.review.ReviewRepository;
import com.example.Backend.service.category.CategoryService;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.review.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
//    @Test
//    public void 리뷰_등록_확인 (){
//        List<String> savedFilePaths = Arrays.asList(
//                "src/assets/noticeImages/image1.jpg",
//                "src/assets/noticeImages/image1.jpg",
//                "src/assets/noticeImages/image1.jpg"
//        );
//        assertTrue(reviewService.register(new ReviewRegisterRequest(
//                1, 1, "sfsjkdfk", "fjdof","dldld",1,"sdjfjdfjkl", savedFilePaths
//        )));
//    }

    }


