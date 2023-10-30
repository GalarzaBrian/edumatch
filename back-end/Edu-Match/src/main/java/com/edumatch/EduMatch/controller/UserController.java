package com.edumatch.EduMatch.controller;

import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.request.UserRequest;
import com.edumatch.EduMatch.models.response.UserResponse;
import com.edumatch.EduMatch.repository.UserRepository;
import com.edumatch.EduMatch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.findAllUsers();
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        String email = authentication.getName();
        Optional<UserEntity> id = userRepository.findByEmail(email);
        UserEntity foundUser = userRepository.findById(id.get().getId()).orElseThrow();

        userService.deleteUser(foundUser.getId());
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserDetails(@PathVariable Long id){
        return userService.findUserById(id);

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@Valid @RequestBody UserRequest request) {
        userService.updateUser(request);
    }
}