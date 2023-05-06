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
    @Column(length = 100, nullable = false)
    private Long questionCommentId;

    @Lob
    private String comment;

    @Column(length = 100, nullable = false)
    private String writer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_board_id")
    private QuestionBoard questionBoard;

    /*
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
*/

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
    /*
    questionCommentId말고 questionBoardId를 생성자에 넣을 수가 없음.. 타입이 달라서
    어떻게?? 그냥 이렇게 하면 question_board_Id가 안 들어감..
    public QuestionComment(Long questionBoardId, String comment, String writer){
        this.comment = comment;
        this.questionBoard = new QuestionBoard(questionBoardId);
        this.writer = writer;
    }*/

    public QuestionComment(Long questionCommentId, String comment, String writer){
        this.comment = comment;
        this.questionCommentId = questionCommentId;
        this.writer = writer;
    }


    public String toString(){
        return "QuestionComment{ " +
                "questionCommentId=" + questionCommentId +
                ", writer='" + writer + '\'' +
                ", comment='" + comment + '\'' +
                ", questionBoard" + questionBoard +
                '}';
    }

}
