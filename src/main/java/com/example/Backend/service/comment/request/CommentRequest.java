package com.example.Backend.service.comment.request;

import com.example.Backend.entity.boards.QuestionBoard;
import com.example.Backend.entity.boards.QuestionComment;
import com.example.Backend.entity.member.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CommentRequest {

    final private String comment;

    //final private QuestionBoard question_no;
    final private Long question_no;
    //final private Member member_no;
    final private Long member_no;

    public QuestionComment toComment() {
        QuestionComment questionComment = new QuestionComment(comment);
        return questionComment;
    }

}
