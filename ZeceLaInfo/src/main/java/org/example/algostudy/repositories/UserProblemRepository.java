package org.example.algostudy.repositories;

import org.example.algostudy.models.UserProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProblemRepository extends JpaRepository<UserProblem, Long> {
    List<UserProblem> findByUserId(Long userId);
    List<UserProblem> findByProblemId(Long problemId);
    Optional<UserProblem> findByUserIdAndProblemId(Long userId, Long problemId);
}
