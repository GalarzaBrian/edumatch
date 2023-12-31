package com.edumatch.EduMatch.models.response;

import com.edumatch.EduMatch.models.ProjectEntity;
import com.edumatch.EduMatch.models.UserEntity;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectResponse {

    private Long id;

    private String name;

    private String description;

    private String studyArea;

    private String requirements;

    private OffsetDateTime endDate;

    private boolean isActive;

    private List<UserEntity> users;

    public static ProjectResponse toDTO(ProjectEntity entity){
        if (entity == null) return null;

        return ProjectResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .requirements(entity.getRequirements())
                .endDate(entity.getEndDate())
                .studyArea(entity.getStudyArea())
                .description(entity.getDescription())
                .users(List.of(UserEntity.builder().id(entity.getId()).build()))
                .build();
    }

    public static List<ProjectResponse> listToDTO(List<ProjectEntity> entities){
        List<ProjectResponse> result = new ArrayList<>();

        for (ProjectEntity temp : entities){

            ProjectResponse obj = new ProjectResponse();
            obj.setName(temp.getName());
            obj.setDescription(temp.getDescription());
            obj.setEndDate(temp.getEndDate());
            obj.setStudyArea(temp.getStudyArea());
            obj.setRequirements(temp.getRequirements());

            result.add(obj);

        }

        return result;
    }
}
