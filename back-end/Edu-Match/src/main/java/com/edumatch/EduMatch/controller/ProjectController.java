package com.edumatch.EduMatch.controller;

import com.edumatch.EduMatch.models.response.ProjectResponse;
import com.edumatch.EduMatch.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
