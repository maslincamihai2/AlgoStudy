package org.example.algostudy.repositories;

import org.example.algostudy.models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Query("SELECT p FROM Problem p WHERE p.category.id = :categoryId")
    List<Problem> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Problem p JOIN p.category c WHERE c.name = :categoryName")
    List<Problem> findByCategoryName(@Param("categoryName") String categoryName);  // Custom query for finding by category name
}
