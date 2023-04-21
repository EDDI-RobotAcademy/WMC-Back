package com.example.Backend.service.boards.response;

import com.example.Backend.entity.boards.QuestionImageData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class BoardResponse {

    private Long questionBoardId;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private String questionCategoryType;
    private List<QuestionImageData> images;



}
