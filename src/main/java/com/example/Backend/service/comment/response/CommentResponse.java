package com.example.Backend.service.comment.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommentResponse {

    final private String comment;

    final private Long question_no;

    final private Long member_no;


}
