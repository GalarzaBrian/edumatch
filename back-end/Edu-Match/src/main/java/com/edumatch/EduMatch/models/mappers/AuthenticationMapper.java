package com.edumatch.EduMatch.models.mappers;

import com.edumatch.EduMatch.models.RoleEntity;
import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.request.RegisterRequest;
import com.edumatch.EduMatch.models.response.AuthenticationResponse;
import com.edumatch.EduMatch.models.response.RegisterResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationMapper {

    public UserEntity registerRequestDTO2Entity(RegisterRequest userDto) {
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());
        newUser.setPhoto(userDto.getPhoto());
        newUser.setRoles(List.of(RoleEntity.builder().id(userDto.getRoleId()).build()));
        return newUser;
    }

    public RegisterResponse entity2RegisterResponseDTO(UserEntity userEntity, String jwt) {
        return RegisterResponse.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .jwt(jwt)
                .build();
    }

    public AuthenticationResponse userDetailsAndJwt2LoginResponseDTO(UserDetails userInContext, String jwt) {
        return AuthenticationResponse.builder()
                .email(userInContext.getUsername())
                .jwt(jwt)
                .build();
    }

}