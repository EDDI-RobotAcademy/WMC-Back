package com.example.Backend.service.category;

import com.example.Backend.entity.boards.QuestionCategory;
import com.example.Backend.repository.jpa.category.QuestionCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionCategoryServiceImpl implements QuestionCategoryService{

    private final QuestionCategoryRepository questionCategoryRepository;

    @Transactional
    public List<QuestionCategory> getAllQuestionCategories() {
        List<QuestionCategory> questionCategories = questionCategoryRepository.findAll();
        for (QuestionCategory questionCategory : questionCategories) {
            questionCategory.getQuestionBoardList().size();
        }
        return questionCategories;
    }

    public QuestionCategory getQuestionCategoryById(Long id){
        return questionCategoryRepository.findById(id).orElse(null);
    }
    public QuestionCategory createQuestionCategory(String name){
        QuestionCategory questionCategory = new QuestionCategory(name);
        return questionCategoryRepository.save(questionCategory);
    }



}
