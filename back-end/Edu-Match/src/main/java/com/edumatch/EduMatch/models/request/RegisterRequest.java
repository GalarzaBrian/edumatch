package com.edumatch.EduMatch.models.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@Builder
public class RegisterRequest {

    private Long dni;

    @Email
    private String email;

    @NotEmpty
    private String password;

    private Long roleId;
}