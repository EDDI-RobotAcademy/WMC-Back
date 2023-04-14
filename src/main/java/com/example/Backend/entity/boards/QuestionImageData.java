package com.example.Backend.entity.boards;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class QuestionImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id") // image table안에서 새로 생기는 것
    private QuestionBoard questionBoard;

    @Column
    private String imageDate;
}
