package com.example.Backend.service.boards.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//@Getter
//@Setter
@Data
@AllArgsConstructor
public class BoardListResponse {

    private Long questionBoardId;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private String questionCategoryType;
    private String firstPhoto;

}
