package com.example.Backend.entity.review;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name="review_id")
    @ManyToOne
    private Review review;
    @Column
    private String reviewImage;
}
