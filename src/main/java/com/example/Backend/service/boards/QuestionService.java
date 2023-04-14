package com.example.Backend.service.boards;

import com.example.Backend.service.boards.request.BoardRequest;
import com.example.Backend.service.boards.response.BoardListResponse;

import java.util.List;

public interface QuestionService {

    Boolean register(BoardRequest boardRequest);

   // public void register(BoardRequest boardRequest);

    //제어자 public이 인터페이스 멤버와 중복된다? - 인터페이스의 멤버는 public이 되므로 public쓸 필요 x
    //public List<QuestionBoard> questionList();
    List<BoardListResponse> getAllQuestion();
    List<BoardListResponse> getQuestionsByCategory(Long questionCategoryId);
}
