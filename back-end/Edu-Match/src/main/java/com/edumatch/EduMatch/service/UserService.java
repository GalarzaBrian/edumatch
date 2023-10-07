package com.edumatch.EduMatch.service;

import com.edumatch.EduMatch.models.request.UserRequest;
import com.edumatch.EduMatch.models.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long id);

    void updateUser(UserRequest request, Long id);

    void deleteUser(Long id);
}
