package com.example.Backend.entity.review;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReviewId;

    @Column(length = 128, nullable = false)
    private String title;
    @Column(length = 128, nullable = false)
    private String writer;
    @Column(length = 128, nullable = false)
    private String content;


    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;


    public Review(String title, String writer, String content){
        this.title = title;
        this.writer= writer;
        this.content=content;

    }

}
