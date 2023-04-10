package com.example.Backend.service.boards;

import com.example.Backend.entity.boards.QuestionCategory;
import com.example.Backend.entity.boards.QuestionImageData;
import com.example.Backend.entity.boards.QuestionBoard;
import com.example.Backend.repository.boards.QuestionImageDataRepository;
import com.example.Backend.repository.boards.QuestionRepository;
import com.example.Backend.service.boards.request.BoardRequest;
import com.example.Backend.service.boards.response.BoardListResponse;
import com.example.Backend.service.category.QuestionCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    final private QuestionRepository questionRepository;

    final private QuestionImageDataRepository questionImageDataRepository;

    final private QuestionCategoryService questionCategoryService;


    public Boolean register(BoardRequest boardRequest) {
        final QuestionBoard questionBoard = boardRequest.toBoard();
        questionRepository.save(questionBoard);

        return true;
    }

    public List<BoardListResponse> getAllQuestion() {
        List<QuestionBoard> boards = questionRepository.findAll();
        List<BoardListResponse> boardListResponses = new ArrayList<>();

        for (QuestionBoard board : boards) {
            String firstPhoto = null;
            List<QuestionImageData> images = questionImageDataRepository.findAllImagesByQuestionBoardId(board.getQuestionBoardId());
            if (!images.isEmpty()) {
                firstPhoto = images.get(0).getImageDate();
            }

            BoardListResponse response = new BoardListResponse(
                    board.getQuestionBoardId(),
                    board.getTitle(),
                    board.getContent(),
                    board.getWriter(),
                    board.getRegDate(),
                    firstPhoto

            );
            boardListResponses.add(response);

        }
        return boardListResponses;

    }

    @Override
    @Transactional
    public List<BoardListResponse> getQuestionsByCategory(Long questionCategoryId){
        QuestionCategory questionCategory = questionCategoryService.getQuestionCategoryById(questionCategoryId);
        List<QuestionBoard> boards = questionCategory.getQuestionBoardList();
        List<BoardListResponse> boardListResponses = new ArrayList<>();
        for (QuestionBoard board : boards ) {
            String firstPhoto = null;
            List<QuestionImageData> questionImages = questionImageDataRepository.findAllImagesByQuestionBoardId(board.getQuestionBoardId());
            if (!questionImages.isEmpty()) {
                firstPhoto = questionImages.get(0).getImageDate();
            }

            BoardListResponse response = new BoardListResponse(
                    board.getQuestionBoardId(),
                    board.getTitle(),
                    board.getContent(),
                    board.getWriter(),
                    board.getRegDate(),
                    firstPhoto
            );
            boardListResponses.add(response);
        }

        return boardListResponses;
    }





}

