package com.example.Backend.entity.boards;

import com.example.Backend.entity.member.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
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
    private Date regDate;
    @UpdateTimestamp
    private Date updDate;
}
