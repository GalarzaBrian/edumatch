package com.edumatch.EduMatch.Repository;

import com.edumatch.EduMatch.Models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(String roleName);
}