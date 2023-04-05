package com.example.Backend.repository.category;

import com.example.Backend.entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
