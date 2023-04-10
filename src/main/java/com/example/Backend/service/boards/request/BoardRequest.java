package com.example.Backend.service.boards.request;

import com.example.Backend.entity.boards.QuestionImageData;
import com.example.Backend.entity.boards.QuestionBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardRequest {

    final private String title;
    final private String content;
    final private String writer;

    final private List<String> savedFilePaths;

    //Service에서 사용하는 것
    public QuestionBoard toBoard() {

        QuestionBoard questionBoard = new QuestionBoard(title, content, writer);
        List<QuestionImageData> imageDataList = new ArrayList<>();

        for (String path : savedFilePaths) {
            QuestionImageData imageData = new QuestionImageData();
            imageData.setImageDate(path);
            questionBoard.addImageData(imageData);
        }

        return questionBoard;

    }

}