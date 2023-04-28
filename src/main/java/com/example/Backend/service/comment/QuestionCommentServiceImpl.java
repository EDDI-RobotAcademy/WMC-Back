package com.example.Backend.service.comment;

import com.example.Backend.entity.boards.QuestionComment;
import com.example.Backend.repository.jpa.boards.QuestionCommentRepository;
import com.example.Backend.service.comment.request.CommentRequest;
import com.example.Backend.service.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionCommentServiceImpl implements QuestionCommentService{

    final private QuestionCommentRepository questionCommentRepository;

    public Boolean register(CommentRequest commentRequest) {
        final QuestionComment questionComment = commentRequest.toComment();
        questionCommentRepository.save(questionComment);
        return true;

    }

    public List<CommentResponse> getAllComment() {
        List<QuestionComment> comments = questionCommentRepository.findAll();
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (QuestionComment comment : comments) {
            CommentResponse response = new CommentResponse(
                    comment.getComment(),
                    comment.getQuestionCommentNo(),
                    comment.getMember().getId()
                    //comment.getRegDate(),
                    //comment.getUpdDate(),
                    //comment.getQuestionBoard(),
                    //comment.getQuestionBoard().getQuestionBoardId(),
                    //comment.getMember().getId()

            );
            commentResponses.add(response);
        }
        return commentResponses;

    }
}
