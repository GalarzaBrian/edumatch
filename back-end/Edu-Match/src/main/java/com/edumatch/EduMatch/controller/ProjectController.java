package com.edumatch.EduMatch.controller;

import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.request.ProjectRequest;
import com.edumatch.EduMatch.models.response.ProjectResponse;
import com.edumatch.EduMatch.models.response.UserInProjectResponse;
import com.edumatch.EduMatch.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
@Validated
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectResponse> getAllProjects(){
        return projectService.findAllProjects();
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectResponse> getAllProjectsByCreator(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        String email = authentication.getName();
        return projectService.findAllProjectsByCreator(email);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponse getProjectDetails(@PathVariable Long id){
        return projectService.findProjectById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProject(@Valid @RequestBody ProjectRequest request,
                              @PathVariable Long id,
                              @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        String email = authentication.getName();
        projectService.updateProject(request,id,email);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable long id,
                              @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        String email = authentication.getName();
        projectService.deleteProject(id, authentication.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProject( @Valid @RequestBody ProjectRequest request){
        projectService.saveProject(request);
    }

    //
    @PostMapping("/{id}/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void applyForProject(@PathVariable Long id,
                                @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        String email = authentication.getName();
        projectService.applyForProject(id, email);

    }

    //Obtener los usuarios anotados al proyecto
    @GetMapping("/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public Set<UserInProjectResponse> findUserByProject(@PathVariable Long id){
        Set<UserEntity> estudiantes = projectService.findUserByProject(id);
        return UserInProjectResponse.toListDTO(estudiantes);
    };

}
