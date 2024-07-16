package org.example.algostudy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "problems")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "input_example")
    private String inputExample;

    @Column(name = "output_example")
    private String outputExample;

    @Column(nullable = false)
    private int points;

    @Column(name = "points_to_unlock_category", nullable = false)
    private int pointsToUnlockCategory;

    @Column(name = "points_to_unlock_total", nullable = false)
    private int pointsToUnlockTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
