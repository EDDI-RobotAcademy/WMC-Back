package com.example.Backend.controller.boards;

import com.example.Backend.entity.boards.QuestionBoard;
import com.example.Backend.service.boards.request.BoardRequest;
import com.example.Backend.service.boards.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questionBoard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QuestionBoardController {
    final private QuestionService questionService;

    public QuestionBoardController(QuestionService questionBoardService) {
        this.questionService = questionBoardService;
    }
    @PostMapping("/register")
    public void productRegister (@RequestBody BoardRequest BoardRequest) {
        log.info("QuestionBoardRegister() 동작");

        questionService.register(BoardRequest);
    }
/*
    @GetMapping("/list")
    public List<QuestionBoard> questionBoardList () {
        log.info("questionBoardList()");

        return questionService.questionList();
    }
*/


}
