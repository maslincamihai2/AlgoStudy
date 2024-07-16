package org.example.algostudy.services.implementation;

import org.example.algostudy.models.Solution;
import org.example.algostudy.models.UserProblem;
import org.example.algostudy.repositories.SolutionRepository;
import org.example.algostudy.repositories.UserProblemRepository;
import org.example.algostudy.services.interfaces.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private UserProblemRepository userProblemRepository;

    @Override
    public Solution saveSolution(Solution solution) {
        return solutionRepository.save(solution);
    }

    @Override
    public void deleteById(Long id) {
        solutionRepository.deleteById(id);
    }

    @Override
    public Optional<Solution> findById(Long id) {
        return solutionRepository.findById(id);
    }

    @Override
    public List<Solution> findAllSolutions() {
        return solutionRepository.findAll();
    }

    @Override
    public List<Solution> findByUserId(Long userId) {
        return solutionRepository.findByUserId(userId);
    }

    @Override
    public List<Solution> findByProblemId(Long problemId) {
        return solutionRepository.findByProblemId(problemId);
    }

    @Override
    public UserProblem saveUserProblem(UserProblem userProblem) {
        return userProblemRepository.save(userProblem);
    }

    @Override
    public Optional<UserProblem> findUserProblem(Long userId, Long problemId) {
        return userProblemRepository.findByUserIdAndProblemId(userId, problemId);
    }
}
