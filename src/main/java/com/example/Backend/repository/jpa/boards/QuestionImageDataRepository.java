package com.example.Backend.repository.jpa.boards;

import com.example.Backend.entity.boards.QuestionImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface QuestionImageDataRepository extends JpaRepository<QuestionImageData, Long> {

    @Query("select i from QuestionImageData i join i.questionBoard q where q.questionBoardId = :questionBoardId")
    List<QuestionImageData> findAllImagesByQuestionBoardId(Long questionBoardId);
}
