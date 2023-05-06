package com.example.Backend.service.comment.response;

import com.example.Backend.entity.member.Member;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentResponse {


    private Long questionBoardId;
    private Long questionCommentId;
    private String comment;
    private String writer;
    private Date regDate;
    private Date udpDate;

    //private Long memberId;




}
