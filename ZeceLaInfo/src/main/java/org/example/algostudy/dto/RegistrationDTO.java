package org.example.algostudy.dto;

public class RegistrationDTO {
    private String email;
    private String password;
    private String username;
    private int onboardingScore;

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOnboardingScore() {
        return onboardingScore;
    }

    public void setOnboardingScore(int onboardingScore) {
        this.onboardingScore = onboardingScore;
    }
}
