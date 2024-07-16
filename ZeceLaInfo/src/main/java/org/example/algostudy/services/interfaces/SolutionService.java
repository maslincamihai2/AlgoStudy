package org.example.algostudy.services.interfaces;

import org.example.algostudy.models.Solution;
import org.example.algostudy.models.UserProblem;

import java.util.List;
import java.util.Optional;

public interface SolutionService {
    Solution saveSolution(Solution solution);
    Optional<Solution> findById(Long id);
    List<Solution> findAllSolutions();
    List<Solution> findByProblemId(Long problemId);
    List<Solution> findByUserId(Long userId);  // Add this method to the interface
    void deleteById(Long id);
    UserProblem saveUserProblem(UserProblem userProblem);
    Optional<UserProblem> findUserProblem(Long userId, Long problemId);
}
