package org.example.algostudy.controllers;

import org.example.algostudy.models.UserProblem;
import org.example.algostudy.security.JwtTokenUtil;
import org.example.algostudy.services.interfaces.UserProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-problems")
public class UserProblemController {

    @Autowired
    private UserProblemService userProblemService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> createUserProblem(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody UserProblem userProblem) {
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            UserProblem savedUserProblem = userProblemService.saveUserProblem(userProblem);
            return ResponseEntity.ok(savedUserProblem);
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProblem> getUserProblemById(@PathVariable Long id) {
        Optional<UserProblem> userProblem = userProblemService.findById(id);
        return userProblem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserProblem>> getUserProblemsByUserId(@PathVariable Long userId) {
        List<UserProblem> userProblems = userProblemService.findByUserId(userId);
        return ResponseEntity.ok(userProblems);
    }

    @GetMapping("/problem/{problemId}")
    public ResponseEntity<List<UserProblem>> getUserProblemsByProblemId(@PathVariable Long problemId) {
        List<UserProblem> userProblems = userProblemService.findByProblemId(problemId);
        return ResponseEntity.ok(userProblems);
    }
}
