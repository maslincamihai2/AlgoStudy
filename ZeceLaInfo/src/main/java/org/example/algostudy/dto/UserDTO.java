package org.example.algostudy.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<SolutionDTO> solutions;
    private List<UserProblemDTO> userProblems;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SolutionDTO> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<SolutionDTO> solutions) {
        this.solutions = solutions;
    }

    public List<UserProblemDTO> getUserProblems() {
        return userProblems;
    }

    public void setUserProblems(List<UserProblemDTO> userProblems) {
        this.userProblems = userProblems;
    }
}
