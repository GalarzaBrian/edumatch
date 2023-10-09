package com.edumatch.EduMatch.controller;

import com.edumatch.EduMatch.models.request.UserRequest;
import com.edumatch.EduMatch.models.response.UserResponse;
import com.edumatch.EduMatch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.findAllUsers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserDetails(@PathVariable Long id){
        return userService.findUserById(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@Valid @RequestBody UserRequest request, @PathVariable Long id) {
        userService.updateUser(request, id);
    }
}