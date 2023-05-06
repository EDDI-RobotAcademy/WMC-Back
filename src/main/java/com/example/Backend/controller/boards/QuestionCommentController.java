package com.example.Backend.controller.boards;

import com.example.Backend.controller.boards.form.QuestionCommentForm;
import com.example.Backend.service.comment.QuestionCommentService;
import com.example.Backend.service.comment.request.CommentRequest;
import com.example.Backend.service.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("questionBoard/comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QuestionCommentController {

    final private QuestionCommentService questionCommentService;


    @GetMapping("/{questionBoardId}")
    public List<CommentResponse> questionCommentList(@PathVariable("questionBoardId") Long questionBoardId) {
        log.info("questionBoardId() 동작");

        return questionCommentService.questionCommentList(questionBoardId);
        //return questionCommentService.getAllComment();
    }

    @PostMapping("/register")
    public boolean commentRegister(@RequestBody QuestionCommentForm form) throws IOException {
        CommentRequest request = form.toCommentRequest();
        log.info("commentRegister() 동작" + form);
        log.info("qnaCommentRegister() 해당 게시물 아이디 : " + request.getQuestionBoardId());
        log.info(request.getComment());
        log.info(request.getWriter());

        return questionCommentService.register(request);
    }

    // 댓글 삭제
    @DeleteMapping("/{questionCommentId}")
    public boolean  commentDelete(@PathVariable("questionCommentId") Long questionCommentId) {
        log.info("commentDelete()");

        return questionCommentService.commentDelete(questionCommentId);
    }

}

