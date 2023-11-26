package com.edumatch.EduMatch.repository;

import com.edumatch.EduMatch.models.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findAllByCreatedBy(String Email);
    Optional<ProjectEntity> findByName(String name);

}
