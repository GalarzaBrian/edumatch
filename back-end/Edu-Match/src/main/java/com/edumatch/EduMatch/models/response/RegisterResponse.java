package com.edumatch.EduMatch.models.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterResponse {

//    private String firstName;
//
//    private String lastName;

    private String email;

    private String jwt;

}