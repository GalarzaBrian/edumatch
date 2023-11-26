package com.edumatch.EduMatch.models.request;

import com.edumatch.EduMatch.models.ProjectEntity;
import lombok.*;

import java.time.OffsetDateTime;

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
    public boolean getIsActive() {
        return isActive;
    }

    private boolean isActive;


    public static ProjectEntity updateEntity(ProjectRequest request, ProjectEntity foundProject){
        foundProject.setName(request.getName());
        foundProject.setDescription(request.getDescription());
        foundProject.setRequirements(request.getRequirements());
        foundProject.setIsActive(request.getIsActive());
        foundProject.setStudyArea(request.getStudyArea());
        foundProject.setEndDate(request.getEndDate());

        return foundProject;
    }

}
