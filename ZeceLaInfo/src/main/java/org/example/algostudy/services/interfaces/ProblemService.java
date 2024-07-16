package org.example.algostudy.services.interfaces;

import org.example.algostudy.dto.ProblemDTO;
import org.example.algostudy.models.Problem;

import java.util.List;
import java.util.Optional;

public interface ProblemService {
    Problem saveProblem(Problem problem);
    Optional<Problem> findById(Long id);

    List<ProblemDTO> findByCategoryId(Long categoryId);
    List<Problem> findAllProblems();

    List<ProblemDTO> findByCategoryName(String categoryName);  // Add this method to the interface
    void deleteById(Long id);

    List<Problem> findUnlockedProblemsByCategory(Long userId, Long categoryId);
    int calculateUserCategoryPoints(Long userId, Long categoryId);
    int calculateUserCategoryProbSolved(Long userId, Long categoryId);
    int calculateUserTotalPoints(Long userId);
    int calculateCategoryTotalPoints(Long categoryId);
    int countProblemsByCategory(Long categoryId);
}
