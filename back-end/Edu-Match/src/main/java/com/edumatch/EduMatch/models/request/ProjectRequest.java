package com.edumatch.EduMatch.models.request;

import com.edumatch.EduMatch.models.ProjectEntity;
import com.edumatch.EduMatch.models.RoleEntity;
import com.edumatch.EduMatch.models.UserEntity;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectRequest {

    private String name;

    private String description;

    private String studyArea;

    private String requirements;

    private OffsetDateTime endDate;

    private boolean isActive;


    public static ProjectEntity updateEntity(ProjectRequest request, ProjectEntity foundProject){
        foundProject.setName(request.getName());
        foundProject.setDescription(request.getDescription());
        foundProject.setRequirements(request.getRequirements());
        foundProject.setIsActive(request.isActive());
        foundProject.setStudyArea(request.getStudyArea());

        return foundProject;
    }

}
