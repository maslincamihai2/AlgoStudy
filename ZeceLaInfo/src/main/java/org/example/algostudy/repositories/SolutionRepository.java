package org.example.algostudy.repositories;

import org.example.algostudy.models.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    @Query("SELECT s FROM Solution s WHERE s.problem.id = :problemId")
    List<Solution> findByProblemId(@Param("problemId") Long problemId);

    @Query("SELECT s FROM Solution s WHERE s.user.id = :userId")
    List<Solution> findByUserId(@Param("userId") Long userId);  // Custom query for finding by user ID
}
