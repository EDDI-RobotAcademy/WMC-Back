package com.example.Backend.entity.boards;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor

public class QuestionBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionBoardId;
    @Column(length = 128, nullable = false)
    private String title;
    @Column(length = 32, nullable = false)
    private String writer;
    @Lob
    private String content;
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date regDate;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date updDate;

    // request에서 요청한 것
    public QuestionBoard(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
