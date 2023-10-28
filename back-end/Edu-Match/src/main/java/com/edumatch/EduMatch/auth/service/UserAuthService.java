package com.edumatch.EduMatch.auth.service;

import com.edumatch.EduMatch.models.request.AuthenticationRequest;
import com.edumatch.EduMatch.models.response.AuthenticationResponse;
import com.edumatch.EduMatch.models.response.UserResponse;


public interface UserAuthService {

    AuthenticationResponse loginAttempt(AuthenticationRequest authenticationRequest);

    UserResponse meData(String authorization);
}