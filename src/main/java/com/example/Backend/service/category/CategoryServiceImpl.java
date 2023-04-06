package com.example.Backend.service.category;

import com.example.Backend.entity.product.Category;
import com.example.Backend.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            category.getProductList().size();
        }
        return categories;
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public Category createCategory(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }
}
