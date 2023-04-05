package com.example.Backend.service.boards.request;

import com.example.Backend.entity.boards.QuestionBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardRequest {

    final private String title;
    final private String content;
    final private String writer;

    //Service에서 사용하는 것
    public QuestionBoard toBoard() {

        QuestionBoard questionBoard = new QuestionBoard(title, content, writer);

        return questionBoard;

    }

}