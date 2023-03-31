package com.example.Backend.service.boards;

import com.example.Backend.entity.boards.QuestionBoard;
import com.example.Backend.repository.boards.QuestionRepository;
import com.example.Backend.service.boards.request.BoardRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    final private QuestionRepository questionRepository;


    @Override
    public void register(BoardRequest boardRequest) {
        QuestionBoard questionBoard = new QuestionBoard();

        questionBoard.setTitle(boardRequest.getTitle());
        questionBoard.setWriter(boardRequest.getWriter());
        questionBoard.setContent(boardRequest.getContent());

        questionRepository.save(questionBoard);
    }

   @Override
    public List<QuestionBoard> questionList() {
        return questionRepository.findAllBy();
    }

}

