package com.example.Backend.entity.boards;

import com.example.Backend.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor

public class QuestionComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionCommentNo;

    //@JoinColumn(name = "member_id")
    //@ManyToOne(fetch = FetchType.LAZY, targetEntity = Member.class)
    //private List<Member> member;

    @Lob
    private String comment;

    //@JsonIgnore
    //@ManyToOne(fetch = FetchType.LAZY, targetEntity = QuestionBoard.class)
    //@JoinColumn(name = "question_board_id")
    //private List<QuestionBoard> questionBoard;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_board_id")
    private QuestionBoard questionBoard;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date regDate;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date updDate;
    /*
    public QuestionComment(String comment, QuestionBoard questionBoard, Member member){
        this.comment = comment;
        this.questionBoard = questionBoard;
        this.member = member;
    }*/
    public QuestionComment(String comment){
        this.comment = comment;
    }
/*
    public String toString(){
        return "QuestionComment{ " +
                "questionCommentNo=" + questionCommentNo +
                ", member='" + member + '\'' +
                ", comment='" + comment + '\'' +
                ", questionBoard" + questionBoard +
                '}';
    }
*/
}
