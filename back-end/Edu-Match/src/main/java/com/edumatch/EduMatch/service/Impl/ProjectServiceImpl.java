package com.edumatch.EduMatch.service.Impl;

import com.edumatch.EduMatch.models.ProjectEntity;
import com.edumatch.EduMatch.models.request.ProjectRequest;
import com.edumatch.EduMatch.models.response.ProjectResponse;
import com.edumatch.EduMatch.repository.ProjectRepository;
import com.edumatch.EduMatch.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<ProjectResponse> findAllProjects() {
        List< ProjectEntity> projectEntities= projectRepository.findAll();
        return ProjectResponse.listToDTO(projectEntities);
    }

    @Override
    public ProjectResponse findProjectById(Long id) {
        ProjectEntity foundProject = projectRepository.findById(id).orElseThrow();
        return ProjectResponse.toDTO(foundProject);
    }

    @Override
    public ProjectEntity saveProject(ProjectRequest request) {
        ProjectEntity newProject =  ProjectEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .studyArea(request.getStudyArea())
                .requirements(request.getRequirements())
                .endDate(request.getEndDate())
                .isActive(request.getIsActive())
                .build();
        return projectRepository.save(newProject);
    }

    @Override
    public void updateProject(ProjectRequest request, Long id) {
        ProjectEntity foundProject = projectRepository.findById(id).orElseThrow();

        projectRepository.save(ProjectRequest.updateEntity(request,foundProject));
    }

    @Override
    public void deleteProject(Long id) {
        ProjectEntity foundProject = projectRepository.findById(id).orElseThrow();
        projectRepository.delete(foundProject);
    }
}
