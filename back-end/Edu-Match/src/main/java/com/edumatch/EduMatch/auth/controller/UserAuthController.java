package com.edumatch.EduMatch.auth.controller;

import com.edumatch.EduMatch.auth.service.UserAuthService;
import com.edumatch.EduMatch.auth.service.UserDetailsCustomService;
import com.edumatch.EduMatch.models.request.AuthenticationRequest;
import com.edumatch.EduMatch.models.request.RegisterRequest;
import com.edumatch.EduMatch.models.response.AuthenticationResponse;
import com.edumatch.EduMatch.models.response.RegisterResponse;
import com.edumatch.EduMatch.models.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private UserAuthService userAuthServ;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse signUp(@Valid @RequestBody RegisterRequest userToCreate) {
        return userDetailsCustomService.signupUser(userToCreate);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return userAuthServ.loginAttempt(authenticationRequest);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse meData(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return userAuthServ.meData(authentication.getName());
    }

}
