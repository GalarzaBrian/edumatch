package com.edumatch.EduMatch.controller;

import com.edumatch.EduMatch.models.ProjectEntity;
import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.request.UserRequest;
import com.edumatch.EduMatch.models.response.UserInProjectResponse;
import com.edumatch.EduMatch.models.response.UserResponse;
import com.edumatch.EduMatch.repository.ProjectRepository;
import com.edumatch.EduMatch.repository.UserRepository;
import com.edumatch.EduMatch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ProjectRepository projectRepository;

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

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<ProjectEntity>> getAllTutorialsByTagId(@PathVariable(value = "id") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Not found User  with id = " + userId);
        }

        List<ProjectEntity> projects = projectRepository.findProjectsByUsersId(userId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    //Agregarme a un proyecto
    @PostMapping("/{projectId}/projects")
    public ResponseEntity<UserInProjectResponse> addUser(@PathVariable(value = "projectId") Long projectId,
                                              @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        String email = authentication.getName();
        Optional<UserEntity> user = userRepository.findByEmail(email);
        UserEntity userReq = user.get();
        UserEntity users = projectRepository.findById(projectId).map(foundUser -> {
            long projId = userReq.getId();

            // tag is existed
            if (projId != 0L) {
                UserEntity _users = userRepository.findById(projId)
                        .orElseThrow(() -> new IllegalArgumentException("Id del proyecto no es valido"));
                foundUser.addUser(_users);
                projectRepository.save(foundUser);
                return _users;
            }

            // add and create new Tag
            foundUser.addUser(userReq);
            return userRepository.save(userReq);
            }).orElseThrow();

        return new ResponseEntity<>(UserInProjectResponse.toDTO(users), HttpStatus.CREATED);
    }

    //Eliminar a alguien de un proyecto
    @DeleteMapping("/{userId}/projects/{projectId}")
    public ResponseEntity<HttpStatus> deleteUserFromProject(@PathVariable(value = "projectId") Long projectId,
                                                            @PathVariable(value = "userId") Long userId) {
        ProjectEntity projectFound = projectRepository.findById(projectId)
                .orElseThrow();

        projectFound.removeUser(userId);
        projectRepository.save(projectFound);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Eliminarse de un proyecto
    @DeleteMapping("/{projectId}/projects")
    public ResponseEntity<HttpStatus> deleteMeFromProject(@PathVariable(value = "projectId") Long projectId,
                                                          @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        String email = authentication.getName();
        Optional<UserEntity> user = userRepository.findByEmail(email);
        Long userId = user.get().getId();
        ProjectEntity projectFound = projectRepository.findById(projectId)
                .orElseThrow();

        projectFound.removeUser(userId);
        projectRepository.save(projectFound);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}