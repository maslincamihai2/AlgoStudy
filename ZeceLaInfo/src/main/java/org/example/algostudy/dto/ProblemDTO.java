package org.example.algostudy.dto;

public class ProblemDTO {
    private Long id;
    private String title;
    private String description;
    private String inputExample;
    private String outputExample;
    private int points;
    private int pointsToUnlockCategory;
    private int pointsToUnlockTotal;
    private Long categoryId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInputExample() {
        return inputExample;
    }

    public void setInputExample(String inputExample) {
        this.inputExample = inputExample;
    }

    public String getOutputExample() {
        return outputExample;
    }

    public void setOutputExample(String outputExample) {
        this.outputExample = outputExample;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPointsToUnlockCategory() {
        return pointsToUnlockCategory;
    }

    public void setPointsToUnlockCategory(int pointsToUnlockCategory) {
        this.pointsToUnlockCategory = pointsToUnlockCategory;
    }

    public int getPointsToUnlockTotal() {
        return pointsToUnlockTotal;
    }

    public void setPointsToUnlockTotal(int pointsToUnlockTotal) {
        this.pointsToUnlockTotal = pointsToUnlockTotal;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
