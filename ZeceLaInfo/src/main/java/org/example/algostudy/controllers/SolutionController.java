package org.example.algostudy.controllers;

import org.example.algostudy.dto.SolutionDTO;
import org.example.algostudy.models.Problem;
import org.example.algostudy.models.Solution;
import org.example.algostudy.models.User;
import org.example.algostudy.models.UserProblem;
import org.example.algostudy.security.JwtTokenUtil;
import org.example.algostudy.services.interfaces.ProblemService;
import org.example.algostudy.services.interfaces.SolutionService;
import org.example.algostudy.services.interfaces.UserProblemService;
import org.example.algostudy.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProblemService userProblemService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> createSolution(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody SolutionDTO solutionDto){
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            Optional<User> userOptional = userService.findById(solutionDto.getUserId());
            Optional<Problem> problemOptional = problemService.findById(solutionDto.getProblemId());

            if (userOptional.isPresent() && problemOptional.isPresent()) {
                User user = userOptional.get();
                Problem problem = problemOptional.get();

                Solution solution = new Solution();
                solution.setCode(solutionDto.getCode());
                solution.setScore(compileSource(solutionDto.getCode()));
                solution.setUser(user);
                solution.setProblem(problem);

                // Save the solution
                Solution createdSolution = solutionService.saveSolution(solution);

                // Check and update or create the user problem entry
                Optional<UserProblem> userProblemOptional = userProblemService.findByUserIdAndProblemId(user.getId(), problem.getId());
                UserProblem userProblem;
                if (userProblemOptional.isPresent()) {
                    userProblem = userProblemOptional.get();
                    if (solution.getScore() > userProblem.getHighestScore()) {
                        userProblem.setHighestScore(solution.getScore());
                        userProblemService.saveUserProblem(userProblem);
                    }
                } else {
                    userProblem = new UserProblem();
                    userProblem.setUser(user);
                    userProblem.setProblem(problem);
                    userProblem.setHighestScore(solution.getScore());
                    userProblemService.saveUserProblem(userProblem);
                }

                return ResponseEntity.ok(createdSolution);
            } else {
                return ResponseEntity.status(404).body("User or Problem not found");
            }
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    public int compileSource(String source_code) {
        try {
            int score = Integer.parseInt(source_code);
            if (score >= 0 && score <= 100) {
                return score;
            } else {
                return 100;
            }
        } catch (NumberFormatException e) {
            return 100;
        }
    }

    @GetMapping
    public ResponseEntity<List<SolutionDTO>> listSolutions() {
        List<Solution> solutions = solutionService.findAllSolutions();
        return ResponseEntity.ok(solutions.stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolutionDTO> getSolution(@PathVariable Long id) {
        Optional<Solution> solution = solutionService.findById(id);
        return solution.map(s -> ResponseEntity.ok(convertToDto(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SolutionDTO>> getSolutionsByUserId(@PathVariable Long userId) {
        List<Solution> solutions = solutionService.findByUserId(userId);
        return ResponseEntity.ok(solutions.stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/problem/{problemId}")
    public ResponseEntity<List<SolutionDTO>> getSolutionsByProblemId(@PathVariable Long problemId) {
        List<Solution> solutions = solutionService.findByProblemId(problemId);
        return ResponseEntity.ok(solutions.stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSolution(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody SolutionDTO solutionDto, @PathVariable Long id){
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            Optional<Solution> optionalSolution = solutionService.findById(id);
            if (optionalSolution.isPresent()) {
                Solution solution = optionalSolution.get();
                solution.setCode(solutionDto.getCode());
                solution.setScore(solutionDto.getScore());
                Solution updatedSolution = solutionService.saveSolution(solution);
                return ResponseEntity.ok(convertToDto(updatedSolution));
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSolution(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long id){
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            solutionService.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    private SolutionDTO convertToDto(Solution solution) {
        SolutionDTO dto = new SolutionDTO();
        dto.setId(solution.getId());
        dto.setCode(solution.getCode());
        dto.setScore(solution.getScore());
        dto.setUserId(solution.getUser().getId());
        dto.setProblemId(solution.getProblem().getId());
        return dto;
    }

    private Solution convertToEntity(SolutionDTO dto) {
        Solution solution = new Solution();
        solution.setId(dto.getId());
        solution.setCode(dto.getCode());
        solution.setScore(dto.getScore());
        // Set user and problem based on IDs
        // This part assumes you have methods in your services to fetch entities by ID
        solution.setUser(userService.findById(dto.getUserId()).orElse(null));
        solution.setProblem(problemService.findById(dto.getProblemId()).orElse(null));
        return solution;
    }
}
