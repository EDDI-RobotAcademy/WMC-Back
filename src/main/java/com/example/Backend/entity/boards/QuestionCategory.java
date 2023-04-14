package com.example.Backend.entity.boards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class QuestionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionCategoryId;

    @Column(length = 128, nullable = false)
    private String questionCategoryType;

    @JsonIgnore
    @OneToMany(mappedBy = "questionCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionBoard> questionBoardList = new ArrayList<>();

    public QuestionCategory(String questionCategoryType) {
        this.questionCategoryType = questionCategoryType;
    }

    public QuestionCategory(Long questionCategoryId){

    }

    public void addQuestion(QuestionBoard questionBoard) {
        questionBoard.setQuestionCategory(this);
        questionBoardList.add(questionBoard);
    }

    @Override
    public String toString() {
        return "Category{" +
                "questionCategoryId=" + questionCategoryId +
                ", questionCategoryType='" + questionCategoryType + '\'' +
                '}';
    }


}
