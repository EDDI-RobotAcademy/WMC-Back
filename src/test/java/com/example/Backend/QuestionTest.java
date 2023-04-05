package com.example.Backend;

import com.example.Backend.service.boards.QuestionService;
import com.example.Backend.service.boards.request.BoardRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class QuestionTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void 질물게시글_등록_확인() {
        assertTrue(questionService.register(new BoardRequest(
                "test", "test입니다", "test내용"
        )));
    }

}
