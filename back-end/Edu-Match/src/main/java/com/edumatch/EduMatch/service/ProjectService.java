package com.edumatch.EduMatch.service;

import com.edumatch.EduMatch.models.request.ProjectRequest;
import com.edumatch.EduMatch.models.request.UserRequest;
import com.edumatch.EduMatch.models.response.ProjectResponse;
import com.edumatch.EduMatch.models.response.UserResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectResponse> findAllProjects();

    ProjectResponse findProjectById(Long id);

    void updateProject(ProjectRequest request, Long id);

    void deleteProject(Long id);
}
