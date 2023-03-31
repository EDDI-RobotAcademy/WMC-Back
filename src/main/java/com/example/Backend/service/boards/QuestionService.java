package com.example.Backend.service.boards;

import com.example.Backend.entity.boards.QuestionBoard;
import com.example.Backend.service.boards.request.BoardRequest;

import java.util.List;

public interface QuestionService {

    public void register(BoardRequest BoardRequest);

    public List<QuestionBoard> questionList();
}
