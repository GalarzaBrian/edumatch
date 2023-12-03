package com.edumatch.EduMatch.service.Impl;

import com.edumatch.EduMatch.models.ProjectEntity;
import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.request.ProjectRequest;
import com.edumatch.EduMatch.models.response.ProjectResponse;
import com.edumatch.EduMatch.repository.ProjectRepository;
import com.edumatch.EduMatch.repository.UserRepository;
import com.edumatch.EduMatch.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public List<ProjectResponse> findAllProjects() {
        List< ProjectEntity> projectEntities= projectRepository.findAll();
        return ProjectResponse.listToDTO(projectEntities);
    }

    @Override
    public List<ProjectResponse> findAllProjectsByCreator(String email) {
        List< ProjectEntity> projectEntities= projectRepository.findAllByCreatedBy(email);
        return ProjectResponse.listToDTO(projectEntities);
    }


    @Override
    public ProjectResponse findProjectById(Long id) {
        ProjectEntity foundProject = projectRepository.findById(id).orElseThrow();
        return ProjectResponse.toDTO(foundProject);
    }

    @Override
    public ProjectEntity saveProject(ProjectRequest request) {

        var isProjectExists = projectRepository.findByName(request.getName());
        if (isProjectExists.isPresent()) throw new IllegalArgumentException("El nombre de proyecto ya existe");

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
    public void updateProject(ProjectRequest request, Long id, String user) {
        ProjectEntity foundProject = projectRepository.findById(id).orElseThrow();
        if (!foundProject.getCreatedBy().equals(user)) throw new IllegalArgumentException("No es propietario de proyecto");
        projectRepository.save(ProjectRequest.updateEntity(request,foundProject));
    }

    @Override
    public void deleteProject(Long id, String user) {
        ProjectEntity foundProject = projectRepository.findById(id).orElseThrow();
        if (!foundProject.getCreatedBy().equals(user)) throw new IllegalArgumentException("No es propietario de proyecto");

        projectRepository.delete(foundProject);
    }

    @Override
    public void applyForProject(Long id, String email) {

        UserEntity user = userRepository.findByEmail(email).orElseThrow();

        ProjectEntity project = projectRepository.findById(id).orElseThrow();

        if(project.getUsers().equals(email)){
            throw new IllegalArgumentException("Ya estas anotado al proyecto");
        }

        if(project.getIsActive() && !project.isDeleted()){
            project.getUsers().add(user);
            projectRepository.save(project);
        }
    }

    @Override
    public Set<UserEntity> findUserByProject(Long id) {
        Optional<ProjectEntity> cursoOptional = projectRepository.findById(id);
        return cursoOptional.map(ProjectEntity::getUsers).orElse(null);
    }


}
