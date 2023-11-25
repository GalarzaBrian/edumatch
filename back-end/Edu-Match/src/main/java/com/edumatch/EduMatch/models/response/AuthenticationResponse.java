package com.edumatch.EduMatch.models.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AuthenticationResponse {

    private String email;
    private String jwt;
    private String expDate;
}