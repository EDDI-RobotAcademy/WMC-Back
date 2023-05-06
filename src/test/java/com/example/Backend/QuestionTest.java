package com.example.Backend;

import com.example.Backend.entity.boards.QuestionBoard;
import com.example.Backend.entity.boards.QuestionCategory;
import com.example.Backend.entity.boards.QuestionComment;
import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.product.Category;
import com.example.Backend.entity.product.Product;
import com.example.Backend.repository.jpa.boards.QuestionCommentRepository;
import com.example.Backend.repository.jpa.boards.QuestionRepository;
import com.example.Backend.service.boards.QuestionService;
import com.example.Backend.service.boards.request.BoardRequest;
import com.example.Backend.service.boards.response.BoardResponse;
import com.example.Backend.service.category.QuestionCategoryService;
import com.example.Backend.service.comment.QuestionCommentService;
import com.example.Backend.service.comment.request.CommentRequest;
import com.example.Backend.service.comment.response.CommentResponse;
import com.example.Backend.service.member.MemberService;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuestionTest {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCategoryService questionCategoryService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionCommentService questionCommentService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private QuestionCommentRepository questionCommentRepository;

    @Test
    public void 질문게시글_등록_확인() {
        QuestionCategory testCategory = questionCategoryService.createQuestionCategory("testCategory");

        List<String> savedFilePaths = Arrays.asList(
                "/path/to/test/image1.jpg",
                "/path/to/test/image2.jpg",
                "/path/to/test/image3.jpg"
        );

        assertTrue(questionService.register(new BoardRequest(
                "test", "test입니다", "test내용", testCategory,  savedFilePaths
        )));
    }

    @Test
    public void 질문게시글_읽기() {
        questionRepository.deleteAll();
        assertEquals(0, questionRepository.count());

        QuestionCategory testCategory = questionCategoryService.createQuestionCategory("testCategory");

        List<String> savedFilePaths = Arrays.asList(
                "src/assets/questionImages/carin1.png",
                "src/assets/questionImages/carin2.png",
                "src/assets/questionImages/carin3.png"
        );

        assertTrue(questionService.register(new BoardRequest(
                "test", "test입니다", "test내용", testCategory,  savedFilePaths
        )));

        assertEquals(1, questionRepository.count());

        List<QuestionBoard> questionBoards = questionRepository.findAll();
        QuestionBoard questionBoard = questionBoards.get(0);
        BoardResponse boardResponse = questionService.read(questionBoard.getQuestionBoardId());

        assertEquals(questionBoard.getQuestionBoardId(), boardResponse.getQuestionBoardId());

    }


    @Test
    public void 질문삭제_확인() {
        questionRepository.deleteAll();
        assertEquals(0, questionRepository.count());

        QuestionCategory testCategory = questionCategoryService.createQuestionCategory("testCategory");

        List<String> savedFilePaths = Arrays.asList(
                "src/assets/productImages/carin1.png",
                "src/assets/productImages/carin2.png",
                "src/assets/productImages/carin3.png"
        );

        assertTrue(questionService.register(new BoardRequest(
                "test", "test입니다", "test내용", testCategory,  savedFilePaths)));

        assertEquals(1, questionRepository.count());

        List<QuestionBoard> questionBoards = questionRepository.findAll();
        QuestionBoard questionBoard = questionBoards.get(0);

        assertTrue(questionService.delete(questionBoard.getQuestionBoardId()));
    }

    @Test
    public void Commemt_등록_확인() {

        //Member testMember = memberService.createQuestionMemberId(1L);
        //QuestionBoard testQuestionBoard = questionCommentService.

        assertTrue(questionCommentService.register(new CommentRequest(
                1L, "test", "test"
        )));
    }


    @Test
    public void Comment_리스트_테스트() {
        List<CommentResponse> commentResponses = questionCommentService.getAllComment();
        assertNotNull(commentResponses);
        assertTrue(commentResponses.size() > 0);
    }

    @Test
    public void Comment_삭제_테스트() {
        questionCommentRepository.deleteAll();
        assertEquals(0, questionCommentRepository.count());

        assertTrue(questionCommentService.register(new CommentRequest(
                1L, "test입니다", "test내용")));

        assertEquals(1, questionCommentRepository.count());

        List<QuestionComment> questionComments = questionCommentRepository.findAll();
        QuestionComment questionComment = questionComments.get(0);

        assertTrue(questionCommentService.commentDelete(questionComment.getQuestionCommentId()));
    }
}
