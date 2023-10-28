package com.edumatch.EduMatch.service.Impl;

import com.edumatch.EduMatch.models.RoleEntity;
import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.request.UserRequest;
import com.edumatch.EduMatch.models.response.UserResponse;
import com.edumatch.EduMatch.repository.RoleRepository;
import com.edumatch.EduMatch.repository.UserRepository;
import com.edumatch.EduMatch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return UserResponse.listToDTO(userEntityList);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserById(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow();
        return UserResponse.toDTO(foundUser);
    }

    @Override
    @Transactional
    public void updateUser(UserRequest request, Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow();

        List<Integer> roleId = request.getRoles();
        List<RoleEntity> roleEntities = new ArrayList<>();

        for (Integer roleIntegerId : roleId) {
            roleEntities.add(roleRepository.getById(roleIntegerId));
        }

        userRepository.save(UserRequest.updateEntity(request, foundUser, roleEntities));

    }

    @Transactional
    public void deleteUser(Long id) {
        var foundUser = userRepository.findById(id).orElseThrow();
        userRepository.delete(foundUser);
    }

}