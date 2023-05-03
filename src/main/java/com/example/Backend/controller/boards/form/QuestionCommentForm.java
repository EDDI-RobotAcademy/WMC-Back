package com.example.Backend.controller.boards.form;

import com.example.Backend.service.boards.request.BoardRequest;
import com.example.Backend.service.comment.request.CommentRequest;
import com.example.Backend.service.member.request.MemberLoginRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionCommentForm {

    private String questionBoardId;
    private String comment;
    private String writer;


    public CommentRequest toCommentRequest(){
        return new CommentRequest(Long.parseLong(questionBoardId), comment, writer);
    }


}
