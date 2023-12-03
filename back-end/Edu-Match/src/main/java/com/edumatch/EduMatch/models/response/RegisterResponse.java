package com.edumatch.EduMatch.models.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterResponse {

    private String email;

    private String jwt;

    private String expDate;

}