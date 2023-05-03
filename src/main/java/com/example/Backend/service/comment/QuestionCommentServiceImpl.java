package com.example.Backend.service.comment;

import com.example.Backend.entity.boards.QuestionBoard;
import com.example.Backend.entity.boards.QuestionCategory;
import com.example.Backend.entity.boards.QuestionComment;
import com.example.Backend.entity.member.Member;
import com.example.Backend.repository.jpa.boards.QuestionCommentRepository;
import com.example.Backend.repository.jpa.boards.QuestionRepository;
import com.example.Backend.service.comment.request.CommentRequest;
import com.example.Backend.service.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionCommentServiceImpl implements QuestionCommentService{

    final private QuestionCommentRepository questionCommentRepository;

    final private QuestionRepository questionRepository;

    public Boolean register(CommentRequest commentRequest) {

        final QuestionBoard question = questionRepository.findById(commentRequest.getQuestionBoardId()).orElse(null);
        if (question == null) {
            // 예외 처리 또는 오류 메시지 반환
        }
        final QuestionComment questionComment = commentRequest.toComment();
        questionComment.setQuestionBoard(question); //set안 쓰고 어떻게??
        questionCommentRepository.save(questionComment);
        return true;

         /*
        final QuestionComment questionComment = commentRequest.toComment();
        questionCommentRepository.save(questionComment);
        return true;

         */

    }





    public List<CommentResponse> getAllComment() {
        List<QuestionComment> comments = questionCommentRepository.findAll();
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (QuestionComment comment : comments) {
            CommentResponse response = new CommentResponse(
                    comment.getQuestionBoard().getQuestionBoardId(),
                    comment.getQuestionCommentId(),
                    comment.getComment(),
                    comment.getWriter(),
                    comment.getRegDate(),
                    comment.getUpdDate()
                    //comment.getMember().getId(),


            );
            commentResponses.add(response);
        }
        return commentResponses;

    }


/*
    public List<CommentResponse> questionCommentList(Long questionBoardId) {
        List<QuestionComment> questionCommentList = questionCommentRepository.findCommentByBoardId(questionBoardId);
        List<CommentResponse> commentResponseList = new ArrayList<>();

        for(QuestionComment questionComment: questionCommentList) {
            CommentResponse commentResponse = new CommentResponse(
                    questionComment.getQuestionCommentId(),
                    questionComment.getQuestionBoard().getQuestionBoardId(),
                    questionComment.getComment(),
                    questionComment.getWriter(),
                    questionComment.getRegDate(),
                    questionComment.getUpdDate()

                    //questionComment.getMember().getId()
            );
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;
    }
    */


    /*
    public void register(CommentRequest commentRequest){
        //나중에 상품 관련된 Q&A할 때 findAllCommentByProductId

        Optional<QuestionBoard> maybeQuestionBoard = questionRepository.findById(commentRequest.getQuestionBoardId());
        QuestionComment questionComment = new QuestionComment();

        questionComment.setWriter(commentRequest.getWriter());
        questionComment.setComment(commentRequest.getComment());
        questionComment.setQuestionBoard(maybeQuestionBoard.get());

        questionCommentRepository.save(questionComment);
    }
    */


}
