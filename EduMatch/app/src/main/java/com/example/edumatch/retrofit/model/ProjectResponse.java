package com.example.edumatch.retrofit.model;

public class ProjectResponse {

    private String name;

    private String email;
    private String description;
    private String studyArea;
    private String requirements;
    private String endDate;

    @Override
    public String toString() {
        return "ProjectResponse{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyArea() {
        return studyArea;
    }

    public void setStudyArea(String studyArea) {
        this.studyArea = studyArea;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    private String active;
}


