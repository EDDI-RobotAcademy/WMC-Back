package com.example.Backend.service.boards;

import com.example.Backend.entity.boards.QuestionBoard;
import com.example.Backend.repository.boards.QuestionRepository;
import com.example.Backend.service.boards.request.BoardRequest;
import com.example.Backend.service.boards.response.BoardListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service

public class QuestionServiceImpl implements QuestionService{

    final private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public Boolean register(BoardRequest boardRequest) {
        final QuestionBoard questionBoard = boardRequest.toBoard();
        questionRepository.save(questionBoard);

        return true;
    }
/*
    public List<BoardListResponse> getAllQuestion() {
        List<QuestionBoard> boards = questionRepository.findAll();
        List<BoardListResponse> boardListResponses = new ArrayList<>();

        for (QuestionBoard board : boards) {

            BoardListResponse response = new BoardListResponse(
                    board.getQuestionBoardId(),
                    board.getTitle(),
                    board.getContent(),
                    board.getWriter(),
                    board.getRegDate()
            );
            boardListResponses.add(response);

        }
        return boardListResponses;

    }
*/



}

