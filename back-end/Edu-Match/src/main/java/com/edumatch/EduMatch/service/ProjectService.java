package com.edumatch.EduMatch.service;

import com.edumatch.EduMatch.models.ProjectEntity;
import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.request.ProjectRequest;
import com.edumatch.EduMatch.models.response.ProjectResponse;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    List<ProjectResponse> findAllProjects();
    List<ProjectResponse> findAllProjectsByCreator(String email);
    ProjectResponse findProjectById(Long id);

    ProjectEntity saveProject(ProjectRequest request);

    void updateProject(ProjectRequest request, Long id, String user);

    void deleteProject(Long id,String user);

    void applyForProject(Long id, String email);

    Set<UserEntity> findUserByProject(Long id);

}
