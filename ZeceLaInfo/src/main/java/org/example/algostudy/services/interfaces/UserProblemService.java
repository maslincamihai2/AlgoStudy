package org.example.algostudy.services.interfaces;

import org.example.algostudy.models.UserProblem;

import java.util.List;
import java.util.Optional;

public interface UserProblemService {
    UserProblem saveUserProblem(UserProblem userProblem);
    Optional<UserProblem> findById(Long id);
    List<UserProblem> findByUserId(Long userId);
    List<UserProblem> findByProblemId(Long problemId);

    Optional<UserProblem> findByUserIdAndProblemId(Long userId, Long problemId);
}
