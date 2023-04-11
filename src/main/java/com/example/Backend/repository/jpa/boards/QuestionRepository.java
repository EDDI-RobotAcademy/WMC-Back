package com.example.Backend.repository.jpa.boards;

import com.example.Backend.entity.boards.QuestionBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionBoard, Long> {
    List<QuestionBoard> findAllBy();
}
