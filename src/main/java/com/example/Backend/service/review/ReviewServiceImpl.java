package com.example.Backend.service.review;

import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.product.ImageData;
import com.example.Backend.entity.product.Product;
import com.example.Backend.entity.review.Review;
import com.example.Backend.entity.review.ReviewImageData;
import com.example.Backend.repository.jpa.member.MemberRepository;
import com.example.Backend.repository.jpa.product.ProductRepository;
import com.example.Backend.repository.jpa.review.ReviewImageDataRepository;
import com.example.Backend.repository.jpa.review.ReviewRepository;
import com.example.Backend.service.product.response.ProductListResponse;
import com.example.Backend.service.product.response.ProductResponse;
import com.example.Backend.service.review.Request.ReviewRequest;
import com.example.Backend.service.review.response.ReviewListResponse;
import com.example.Backend.service.review.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    final private ProductRepository productRepository;
    final private MemberRepository memberRepository;
    final private ReviewRepository reviewRepository;
    final private ReviewImageDataRepository reviewImageDataRepository;

    @Override
    public Boolean register(ReviewRequest reviewRequest) {
        // Product와 Member를 조회합니다.
        Product product = productRepository.findById(reviewRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        Member member = memberRepository.findById(reviewRequest.getMemberId())
               .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
            Review review = reviewRequest.toReview(product, member);
            reviewRepository.save(review);
            return true;

    }

    @Override
    @Transactional
    public Boolean delete(Long reviewId) {
        Optional<Review> maybeReview = reviewRepository.findById(reviewId);
        if (maybeReview.isPresent()) {
            Review review = maybeReview.get();
            reviewImageDataRepository.deleteAll(review.getReviewImageDataList());
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }

    @Override
    public List<ReviewListResponse>getAllReviews(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 상품입니다."));
        List<Review> reviews = reviewRepository.findByProduct(product);

        List<ReviewListResponse> reviewListResponses = new ArrayList<>();

        for (Review review : reviews) {
            String firstPhoto = null;
            List<ReviewImageData> images = reviewImageDataRepository.findAllImagesByReviewId(review.getReviewId());
            if (!images.isEmpty()) {
                firstPhoto = images.get(0).getReviewImageData();
            }

            ReviewListResponse response = new ReviewListResponse(
                    review.getReviewId(),
                    review.getProduct().getName(),
                    review.getProduct().getCategory().getName(),
                    review.getMember().getUsername(),
                    review.getRating(),
                    review.getContent(),
                    review.getRegDate(),
                    firstPhoto
            );
            reviewListResponses.add(response);
        }

        return reviewListResponses;
    }
        //find all :모든 리뷰를 다가지고 오는데
        //find by product


    @Override
    public ReviewResponse getReviewById(Long reviewId) {
       Review review = reviewRepository.findById(reviewId)
               .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

        return null;
    }

}
