package com.example.Backend.service.comment;

import com.example.Backend.entity.boards.QuestionCategory;
import com.example.Backend.entity.boards.QuestionComment;
import com.example.Backend.entity.member.Member;
import com.example.Backend.service.boards.response.BoardListResponse;
import com.example.Backend.service.comment.request.CommentRequest;
import com.example.Backend.service.comment.response.CommentResponse;
import com.example.Backend.service.product.request.ProductRegisterRequest;

import java.util.List;

public interface QuestionCommentService {

   Boolean register(CommentRequest commentRequest);

   //List<CommentResponse> questionCommentList(Long questionBoardId);

   List<CommentResponse> getAllComment();

}
