package com.example.Backend.service.comment.request;

import com.example.Backend.entity.boards.QuestionComment;
import com.example.Backend.entity.member.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
public class CommentRequest {

    final private Long questionBoardId;
    final private String comment;
    final private String writer;


    //final private Member member_no;


/*
    final private String title;
    final private Long parentBoardId;
    final private String questionType;
    final private String writer;
    final private String content;
    final private Long memberId;
    final private boolean secret;
    final private Long viewCnt;
    */


    public QuestionComment toComment() {
        QuestionComment questionComment = new QuestionComment(questionBoardId, comment, writer);
        return questionComment;
    }

}
