package com.edumatch.EduMatch.models.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {

    private String email;
    private String jwt;
}