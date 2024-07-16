package org.example.algostudy.controllers;

import org.example.algostudy.dto.CategoryDTO;
import org.example.algostudy.models.Category;
import org.example.algostudy.security.JwtTokenUtil;
import org.example.algostudy.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody Category category) {
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            Category savedCategory = categoryService.saveCategory(category);
            return ResponseEntity.ok(savedCategory);
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        Optional<Category> category = categoryService.findByName(name);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            CategoryDTO categoryDto = convertToDto(category.get());
            return ResponseEntity.ok(categoryDto);
        }
        return ResponseEntity.status(404).body("Category not found");
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    private CategoryDTO convertToDto(Category category) {
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
