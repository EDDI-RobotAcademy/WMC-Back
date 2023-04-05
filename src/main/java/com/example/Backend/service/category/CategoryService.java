package com.example.Backend.service.category;

import com.example.Backend.entity.product.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category createCategory(String name);
}
