package com.example.Backend.entity.review;

import com.example.Backend.entity.boards.QuestionImageData;
import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false )
    private Product product;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_id")
    private Member member;
    @Lob
    private int rating;
    @Column
    private String content;
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ReviewImageData> reviewImageDataList = new ArrayList<>();

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date regDate;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date updDate;


    public Review(Product product , Member member , int rating , String content){
        this.product= product;
        this.member=member;
        this.rating=rating;
        this.content=content;
    }
    public void addReviewImageData(ReviewImageData reviewImageData) {
        reviewImageData.setReview(this);
        reviewImageDataList.add(reviewImageData);
    }


}
