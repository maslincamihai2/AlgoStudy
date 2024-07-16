package org.example.algostudy.services.implementation;

import org.example.algostudy.dto.ProblemDTO;
import org.example.algostudy.models.Problem;
import org.example.algostudy.models.UserProblem;
import org.example.algostudy.repositories.ProblemRepository;
import org.example.algostudy.repositories.UserProblemRepository;
import org.example.algostudy.repositories.UserRepository;
import org.example.algostudy.services.interfaces.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private UserProblemRepository userProblemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Problem saveProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    @Override
    public Optional<Problem> findById(Long id) {
        return problemRepository.findById(id);
    }

    @Override
    public List<ProblemDTO> findByCategoryId(Long categoryId) {
        return problemRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProblemDTO convertToDto(Problem problem) {
        ProblemDTO problemDto = new ProblemDTO();
        problemDto.setId(problem.getId());
        problemDto.setTitle(problem.getTitle());
        problemDto.setDescription(problem.getDescription());
        problemDto.setInputExample(problem.getInputExample());
        problemDto.setOutputExample(problem.getOutputExample());
        problemDto.setPoints(problem.getPoints());
        problemDto.setPointsToUnlockCategory(problem.getPointsToUnlockCategory());
        problemDto.setPointsToUnlockTotal(problem.getPointsToUnlockTotal());
        problemDto.setCategoryId(problem.getCategory().getId());
        return problemDto;
    }

    @Override
    public List<Problem> findAllProblems() {
        return problemRepository.findAll();
    }

    @Override
    public List<ProblemDTO> findByCategoryName(String categoryName) {
        return problemRepository.findByCategoryName(categoryName).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteById(Long id) {
        problemRepository.deleteById(id);
    }

    @Override
    public List<Problem> findUnlockedProblemsByCategory(Long userId, Long categoryId) {
        int totalPoints = calculateUserTotalPoints(userId);
        int categoryPoints = calculateUserCategoryPoints(userId, categoryId);
        List<Problem> allProblems = problemRepository.findByCategoryId(categoryId);

        return allProblems.stream().filter(problem ->
                categoryPoints >= problem.getPointsToUnlockCategory() ||
                        (totalPoints + userRepository.getById(userId).getOnboardingScore() >= problem.getPointsToUnlockTotal())
        ).toList();
    }

    @Override
    public int calculateUserCategoryPoints(Long userId, Long categoryId) {
        List<UserProblem> userProblems = userProblemRepository.findByUserId(userId);
        return userProblems.stream()
                .filter(userProblem -> userProblem.getHighestScore() == 100)
                .filter(userProblem -> Objects.equals(userProblem.getProblem().getCategory().getId(), categoryId))
                .mapToInt(userProblem -> userProblem.getProblem().getPoints())
                .sum();
    }

    @Override
    public int calculateUserCategoryProbSolved(Long userId, Long categoryId) {
        List<UserProblem> userProblems = userProblemRepository.findByUserId(userId);
        return (int) userProblems.stream()
                .filter(userProblem -> userProblem.getHighestScore() == 100)
                .filter(userProblem -> Objects.equals(userProblem.getProblem().getCategory().getId(), categoryId))
                .count();
    }

    @Override
    public int calculateUserTotalPoints(Long userId) {
        List<UserProblem> userProblems = userProblemRepository.findByUserId(userId);
        return userProblems.stream()
                .filter(userProblem -> userProblem.getHighestScore() == 100)
                .mapToInt(userProblem -> userProblem.getProblem().getPoints())
                .sum();
    }

    @Override
    public int calculateCategoryTotalPoints(Long categoryId) {
        List<Problem> problems = problemRepository.findByCategoryId(categoryId);
        return problems.stream()
                .mapToInt(Problem::getPoints)
                .sum();
    }

    @Override
    public int countProblemsByCategory(Long categoryId) {
        return problemRepository.findByCategoryId(categoryId).size();
    }
}
