package com.example.Backend;

import com.example.Backend.entity.boards.QuestionBoard;
import com.example.Backend.entity.boards.QuestionCategory;
import com.example.Backend.repository.jpa.boards.QuestionRepository;
import com.example.Backend.service.boards.QuestionService;
import com.example.Backend.service.boards.request.BoardRequest;
import com.example.Backend.service.boards.response.BoardResponse;
import com.example.Backend.service.category.QuestionCategoryService;
import com.example.Backend.service.comment.QuestionCommentService;
import com.example.Backend.service.comment.request.CommentRequest;
import com.example.Backend.service.notice.request.NoticeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class QuestionTest {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCategoryService questionCategoryService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionCommentService questionCommentService;

    @Test
    public void 질문게시글_등록_확인() {
        QuestionCategory testCategory = questionCategoryService.createQuestionCategory("testCategory");

        List<String> savedFilePaths = Arrays.asList(
                "/path/to/test/image1.jpg",
                "/path/to/test/image2.jpg",
                "/path/to/test/image3.jpg"
        );

        assertTrue(questionService.register(new BoardRequest(
                "test", "test입니다", "test내용", testCategory,  savedFilePaths
        )));
    }

    @Test
    public void 상품읽기() {
        questionRepository.deleteAll();
        assertEquals(0, questionRepository.count());

        QuestionCategory testCategory = questionCategoryService.createQuestionCategory("testCategory");

        List<String> savedFilePaths = Arrays.asList(
                "src/assets/questionImages/carin1.png",
                "src/assets/questionImages/carin2.png",
                "src/assets/questionImages/carin3.png"
        );

        assertTrue(questionService.register(new BoardRequest(
                "test", "test입니다", "test내용", testCategory,  savedFilePaths
        )));

        assertEquals(1, questionRepository.count());

        List<QuestionBoard> questionBoards = questionRepository.findAll();
        QuestionBoard questionBoard = questionBoards.get(0);
        BoardResponse boardResponse = questionService.read(questionBoard.getQuestionBoardId());

        assertEquals(questionBoard.getQuestionBoardId(), boardResponse.getQuestionBoardId());

    }
    @Test
    public void Commemt_등록_확인() {

        //QuestionBoard testQuestionBoard = questionCommentService.

        assertTrue(questionCommentService.register(new CommentRequest(
                "test", 1L, 10L
        )));
    }


}
