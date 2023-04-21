package com.example.Backend.repository.jpa.category;

import com.example.Backend.entity.boards.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Long> {
}
