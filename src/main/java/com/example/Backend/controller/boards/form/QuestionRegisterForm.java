package com.example.Backend.controller.boards.form;

import com.example.Backend.service.boards.request.BoardRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionRegisterForm {
    private String title;
    private String content;
    private String writer;


    public BoardRequest toBoardRequest(){
        return new BoardRequest(title, content, writer);
    }
}
