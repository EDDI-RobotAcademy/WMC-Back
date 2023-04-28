package com.example.Backend.controller.boards;

import com.example.Backend.controller.boards.form.QuestionCommentForm;
import com.example.Backend.service.comment.QuestionCommentService;
import com.example.Backend.service.comment.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/questionBoard/comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QuestionCommentController {

    final private QuestionCommentService questionCommentService;

    @PostMapping(value = "/register",  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean commentRegister (@ModelAttribute QuestionCommentForm form) throws IOException{
        log.info("commentRegister() 동작" + form);

        CommentRequest request = new CommentRequest(form.getComment(), form.getQuestion_no(), form.getMember_no());

        return questionCommentService.register(request);
    }

}
