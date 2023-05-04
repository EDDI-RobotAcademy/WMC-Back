package com.example.Backend.repository.jpa.boards;

import com.example.Backend.controller.boards.QuestionCommentController;
import com.example.Backend.entity.boards.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
    //@Query("select c from QuestionComment c join fetch c.member join fetch c.questionBoard q where q.questionNo = :questionBoardId")
    //List<QuestionComment> findCommentByQuestionNo(Long questionBoardId);
    List<QuestionComment> findAllBy();

    @Query("select qc from QuestionComment qc join fetch qc.questionBoard q where q.questionBoardId = :questionBoardId")
    List<QuestionComment> findCommentByBoardId(Long questionBoardId);

    ///@Query("select c from QuestionComment c join fetch c.member")
    //QuestionComment findByQuestionNo(Long questionBoardId);

    //@Query("select c from QuestionComment c join fetch c.member join fetch c.questionBoard q where q.questionBoardId = :questionBoardId")
    //Optional<QuestionComment> findOptionalByQuestionNo(Long questionBoardId);
}
