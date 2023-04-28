package com.example.Backend.controller.boards.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionCommentForm {

    private String comment;
    private Long question_no;
    private Long member_no;
}
