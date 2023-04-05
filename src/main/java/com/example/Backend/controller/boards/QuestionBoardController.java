package com.example.Backend.controller.boards;

import com.example.Backend.controller.boards.form.QuestionRegisterForm;
import com.example.Backend.service.boards.request.BoardRequest;
import com.example.Backend.service.boards.QuestionService;
import com.example.Backend.service.boards.response.BoardListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questionBoard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QuestionBoardController {

    final private QuestionService questionService;

    @PostMapping("/register")
    public boolean questionRegister (@ModelAttribute QuestionRegisterForm form) throws IOException {
        log.info("questionRegister() 동작");

        BoardRequest request = new BoardRequest(form.getTitle(), form.getWriter(), form.getContent());

        return questionService.register(request);
    }

    @GetMapping("/list")
    public List<BoardListResponse> getAllNotice() {
        log.info("questionBoardList()");
        return questionService.getAllQuestion();
    }
}