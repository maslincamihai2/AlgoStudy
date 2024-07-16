package org.example.algostudy.services.interfaces;

import org.example.algostudy.dto.CategoryDTO;
import org.example.algostudy.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category saveCategory(Category category);
    Optional<Category> findById(Long id);
    List<CategoryDTO> findAll();
    Optional<Category> findByName(String name);
    List<Category> findAllCategories();
}
