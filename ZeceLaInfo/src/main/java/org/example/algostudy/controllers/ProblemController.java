package org.example.algostudy.controllers;

import org.example.algostudy.dto.CategoryPointsDTO;
import org.example.algostudy.dto.ProblemDTO;
import org.example.algostudy.models.Problem;
import org.example.algostudy.models.User;
import org.example.algostudy.security.JwtTokenUtil;
import org.example.algostudy.services.interfaces.ProblemService;
import org.example.algostudy.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> createProblem(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody ProblemDTO problemDto){
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            Problem problem = new Problem();
            problem.setTitle(problemDto.getTitle());
            problem.setDescription(problemDto.getDescription());
            problem.setInputExample(problemDto.getInputExample());
            problem.setOutputExample(problemDto.getOutputExample());
            problem.setPoints(problemDto.getPoints());
            problem.setPointsToUnlockCategory(problemDto.getPointsToUnlockCategory());
            problem.setPointsToUnlockTotal(problemDto.getPointsToUnlockTotal());
            // Assuming category is set via problemDto.getCategory()
            Problem createdProblem = problemService.saveProblem(problem);
            return ResponseEntity.ok(createdProblem);
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    @GetMapping
    public ResponseEntity<List<Problem>> listProblems() {
        List<Problem> problems = problemService.findAllProblems();
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProblem(@PathVariable Long id) {
        Optional<Problem> problem = problemService.findById(id);
        if (problem.isPresent()) {
            ProblemDTO problemDto = convertToDto(problem.get());
            return ResponseEntity.ok(problemDto);
        }
        return ResponseEntity.status(404).body("Problem not found");
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProblemsByCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long categoryId) {
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            Optional<User> userOptional = userService.findByEmail(validatedEmail.get());
            if (userOptional.isPresent()) {
                List<Problem> problems = problemService.findUnlockedProblemsByCategory(userOptional.get().getId(), categoryId);
                return ResponseEntity.ok(problems.stream().map(this::convertToDto).toList());
            }
        }
        return ResponseEntity.status(401).body("Unauthorized");
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProblem(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody ProblemDTO problemDto, @PathVariable Long id){
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            Optional<Problem> optionalProblem = problemService.findById(id);
            if (optionalProblem.isPresent()) {
                Problem problem = optionalProblem.get();
                problem.setTitle(problemDto.getTitle());
                problem.setDescription(problemDto.getDescription());
                problem.setInputExample(problemDto.getInputExample());
                problem.setOutputExample(problemDto.getOutputExample());
                problem.setPoints(problemDto.getPoints());
                problem.setPointsToUnlockCategory(problemDto.getPointsToUnlockCategory());
                problem.setPointsToUnlockTotal(problemDto.getPointsToUnlockTotal());
                // Assuming category is set via problemDto.getCategory()
                Problem updatedProblem = problemService.saveProblem(problem);
                return ResponseEntity.ok(updatedProblem);
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProblem(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long id){
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            problemService.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    @GetMapping("/category/{categoryId}/points")
    public ResponseEntity<?> getCategoryPoints(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long categoryId) {
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            Optional<User> userOptional = userService.findByEmail(validatedEmail.get());
            if (userOptional.isPresent()) {
                Long userId = userOptional.get().getId();
                int categoryPoints = problemService.calculateUserCategoryPoints(userId, categoryId);
                int problemsSolved = problemService.calculateUserCategoryProbSolved(userId, categoryId);
                int totalPoints = problemService.calculateCategoryTotalPoints(categoryId);
                int totalProblems = problemService.countProblemsByCategory(categoryId);
                return ResponseEntity.ok(new CategoryPointsDTO(categoryPoints, totalPoints, totalProblems, problemsSolved));
            }

        }
        return ResponseEntity.status(401).body("Unauthorized");
    }
}
