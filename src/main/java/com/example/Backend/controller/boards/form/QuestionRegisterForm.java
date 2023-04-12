package com.example.Backend.controller.boards.form;

import com.example.Backend.service.boards.request.BoardRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionRegisterForm {
    private String title;
    private String content;
    private String writer;
    private List<MultipartFile> fileList;


    public BoardRequest toBoardRequest(List<String> savedFilePaths){
        return new BoardRequest(title, content, writer, savedFilePaths);
    }
}
