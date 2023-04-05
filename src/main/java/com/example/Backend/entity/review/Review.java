package com.example.Backend.entity.review;

import com.example.Backend.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
//기본 생성자를 만들어 주는 것.

public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(length = 128, nullable = false)
    private String writer;

    @Column
    private float rating;
    @Lob
    private String content;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updateDate;

    //orphanRemoval = true : 연결된 항목이 더 이상 Review 항목에서 참조되지 않을때 제거
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ReviewImage> reviewImageList = new ArrayList<>();

    public Review(String title, String writer, float rating, String content ){
        this.title=title;
        this.writer = writer;
        this.rating = rating;
        this.content = content;
    }
    public void addReviewImage(ReviewImage reviewImage){
        reviewImage.setReview(this);
        reviewImageList.add(reviewImage);
    }

}
