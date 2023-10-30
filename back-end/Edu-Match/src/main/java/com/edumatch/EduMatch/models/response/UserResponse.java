package com.edumatch.EduMatch.models.response;

import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.mappers.RoleMapper;
import com.edumatch.EduMatch.models.request.RoleDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {

    private static final RoleMapper roleMapper = new RoleMapper();

//    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String about;

//    private List<RoleDTO> roles;

    private List<ProjectResponse> projects;

    public static UserResponse toDTO(UserEntity entity) {
        if (entity == null) return null;

        return UserResponse.builder()
//                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .about(entity.getPhoto())
//                .roles(roleMapper.entity2DTO(entity.getRoles()))
                .projects(ProjectResponse.listToDTO(entity.getProjects()))
                .build();

    }

    public static List<UserResponse> listToDTO(List<UserEntity> users) {

        List<UserResponse> result = new ArrayList<>();

        for (UserEntity temp : users) {

            UserResponse obj = new UserResponse();
//            obj.setId(temp.getId());
            obj.setFirstName(temp.getFirstName());
            obj.setLastName(temp.getLastName());
            obj.setEmail(temp.getEmail());
            obj.setAbout(temp.getPhoto());
//            obj.setRoles(roleMapper.entity2DTO(temp.getRoles()));
            obj.setProjects(ProjectResponse.listToDTO(temp.getProjects()));
            result.add(obj);
        }

        return result;

    }
}