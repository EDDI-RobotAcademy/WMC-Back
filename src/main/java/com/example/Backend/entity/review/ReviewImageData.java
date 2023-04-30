package com.example.Backend.entity.review;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class ReviewImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id") // image table안에서 새로 생기는 것
    private Review review;

    @Column
    private String reviewImageData;
}

