package com.edumatch.EduMatch.controller;

import com.edumatch.EduMatch.models.request.ProjectRequest;
import com.edumatch.EduMatch.models.response.ProjectResponse;
import com.edumatch.EduMatch.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponse getProjectDetails(@PathVariable Long id){
        return projectService.findProjectById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProject(@Valid @RequestBody ProjectRequest request, @PathVariable Long id) {
        projectService.updateProject(request,id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable long id){
        projectService.deleteProject(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProject(@RequestBody ProjectRequest request){
        projectService.saveProject(request);
    }

}
