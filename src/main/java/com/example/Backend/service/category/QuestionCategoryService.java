package com.example.Backend.service.category;

import com.example.Backend.entity.boards.QuestionCategory;

import java.util.List;

public interface QuestionCategoryService {

    List<QuestionCategory> getAllQuestionCategories();
    QuestionCategory getQuestionCategoryById(Long id);
    QuestionCategory createQuestionCategory(String name);
}
