package com.example.Backend.entity.boards;


import com.example.Backend.entity.product.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_category_id", nullable = false)
    private QuestionCategory questionCategory;

    // FK
    @OneToMany(mappedBy = "questionBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<QuestionImageData> imageDataList = new ArrayList<>();

    @OneToMany(mappedBy = "questionBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<QuestionComment> questionCommentList = new ArrayList<>();


    // 부모 게시물과의 관계
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_board_id")
    private QuestionBoard parentBoard;

    //자식게시물(답글) 목록
    @JsonIgnore
    @OneToMany(mappedBy = "parentBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionBoard> replies = new ArrayList<>();



    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date regDate;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date updDate;

    // request에서 요청한 것
    public QuestionBoard(String title, String content, String writer, QuestionCategory questionCategory) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.questionCategory = questionCategory;
    }

    public QuestionBoard(Long questionBoardId) {
    }

    // imageData entity를 QuestionBoard class에 넣기
    public void addImageData(QuestionImageData imageData) {
        imageData.setQuestionBoard(this);
        imageDataList.add(imageData);
    }

    public String toString(){
        return "QuestionBoard{ " +
                "questionBoardId=" + questionBoardId +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content" + content +
                '}';
    }

}
