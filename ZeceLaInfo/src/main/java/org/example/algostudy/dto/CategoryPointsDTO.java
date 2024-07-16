package org.example.algostudy.dto;

public class CategoryPointsDTO {
    private int categoryPoints;
    private int totalPoints;
    private int totalProblems;

    private int problemsSolved;

    public CategoryPointsDTO(int categoryPoints, int totalPoints, int totalProblems, int problemsSolved) {
        this.categoryPoints = categoryPoints;
        this.totalPoints = totalPoints;
        this.totalProblems = totalProblems;
        this.problemsSolved = problemsSolved;
    }

    public int getCategoryPoints() {
        return categoryPoints;
    }

    public void setCategoryPoints(int categoryPoints) {
        this.categoryPoints = categoryPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalProblems() {
        return totalProblems;
    }

    public void setTotalProblems(int totalProblems) {
        this.totalProblems = totalProblems;
    }

    public int getProblemsSolved() {
        return problemsSolved;
    }

    public void setProblemsSolved(int problemsSolved) {
        this.problemsSolved = problemsSolved;
    }
}
