package com.example.Backend;

import com.example.Backend.service.category.CategoryService;
import com.example.Backend.service.category.QuestionCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuestionCategoryService questionCategoryService;

    @Test
    public void category_리스트_불러오기() {

        System.out.println("카테고리 리스트 입니다: " + categoryService.getAllCategories());
    }

    @Test
    public void Question_Category_리스트_불러오기() {

        System.out.println("카테고리 리스트 입니다: " + categoryService.getAllCategories());
    }
}
