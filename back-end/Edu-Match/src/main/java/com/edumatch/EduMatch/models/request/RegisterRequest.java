package com.edumatch.EduMatch.models.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@Builder
public class RegisterRequest {

//    @NotEmpty
//    private String firstName;
//
//    @NotEmpty
//    private String lastName;

    private Long dni;

    @Email
    private String email;

    @NotEmpty
    private String password;

//    private OffsetDateTime birthday;
//
//    private String photo;

//    @NotNull
    private Long roleId;
}