package org.example.algostudy.services.implementation;

import org.example.algostudy.models.UserProblem;
import org.example.algostudy.repositories.UserProblemRepository;
import org.example.algostudy.services.interfaces.UserProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProblemServiceImpl implements UserProblemService {

    @Autowired
    private UserProblemRepository userProblemRepository;

    @Override
    public UserProblem saveUserProblem(UserProblem userProblem) {
        return userProblemRepository.save(userProblem);
    }

    @Override
    public Optional<UserProblem> findById(Long id) {
        return userProblemRepository.findById(id);
    }

    @Override
    public List<UserProblem> findByUserId(Long userId) {
        return userProblemRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserProblem> findByUserIdAndProblemId(Long userId, Long problemId) {
        return userProblemRepository.findByUserIdAndProblemId(userId, problemId);
    }
    @Override
    public List<UserProblem> findByProblemId(Long problemId) {
        return userProblemRepository.findByProblemId(problemId);
    }
}
