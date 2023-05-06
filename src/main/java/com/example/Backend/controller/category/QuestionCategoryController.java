package com.example.Backend.controller.category;

import com.example.Backend.entity.boards.QuestionCategory;
import com.example.Backend.entity.product.Category;
import com.example.Backend.service.category.QuestionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categoryList")
public class QuestionCategoryController {

    @Autowired
    private QuestionCategoryService questionCategoryService;

    @GetMapping
    public List<QuestionCategory> getAllQuestionCategories() {
        return questionCategoryService.getAllQuestionCategories();
    }
}
