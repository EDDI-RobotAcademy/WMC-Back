package com.example.Backend.service.boards.request;

import com.example.Backend.entity.boards.QuestionBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class BoardRequest {

    final private String title;
    final private String writer;
    final private String content;

}