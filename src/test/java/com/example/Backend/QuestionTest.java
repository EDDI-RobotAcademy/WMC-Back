//package com.example.Backend;
//
//import com.example.Backend.entity.boards.QuestionCategory;
//import com.example.Backend.service.boards.QuestionService;
//import com.example.Backend.service.boards.request.BoardRequest;
//import com.example.Backend.service.category.QuestionCategoryService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//public class QuestionTest {
//
//    @Autowired
//    private QuestionService questionService;
//    @Autowired
//    private QuestionCategoryService questionCategoryService;
//
//    @Test
//    public void 질문게시글_등록_확인() {
//        QuestionCategory testCategory = questionCategoryService.createQuestionCategory("testCategory");
//
//        List<String> savedFilePaths = Arrays.asList(
//                "/path/to/test/image1.jpg",
//                "/path/to/test/image2.jpg",
//                "/path/to/test/image3.jpg"
//        );
//
//        assertTrue(questionService.register(new BoardRequest(
//                "test", "test입니다", "test내용", testCategory,  savedFilePaths
//        )));
//    }
//
//}
