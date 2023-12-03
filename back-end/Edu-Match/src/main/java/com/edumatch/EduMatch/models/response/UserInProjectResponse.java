package com.edumatch.EduMatch.models.response;

import com.edumatch.EduMatch.models.ProjectEntity;
import com.edumatch.EduMatch.models.UserEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserInProjectResponse {

    private Long id;

    private String email;

    public static UserInProjectResponse toDTO(UserEntity entity){
        if (entity == null) return null;

        return UserInProjectResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .build();
    }

    public static Set<UserInProjectResponse> toListDTO(Set<UserEntity> entity) {
        Set<UserInProjectResponse> result = new HashSet<>();
        for (UserEntity temp : entity){
            UserInProjectResponse obj = new UserInProjectResponse();
            obj.setId(temp.getId());
            obj.setEmail(temp.getEmail());
            result.add(obj);
        }
        return result;
    }
}
