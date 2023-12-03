package com.edumatch.EduMatch.repository;

import com.edumatch.EduMatch.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findUsersByProjectsId(Long projectId);
}
