package com.example.Backend.service.comment;

import com.example.Backend.service.boards.response.BoardListResponse;
import com.example.Backend.service.comment.request.CommentRequest;
import com.example.Backend.service.comment.response.CommentResponse;

import java.util.List;

public interface QuestionCommentService {
    Boolean register(CommentRequest commentRequest);

    List<CommentResponse> getAllComment();


}
