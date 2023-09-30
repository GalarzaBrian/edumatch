package com.edumatch.EduMatch.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity {

    private OffsetDateTime createdDate = OffsetDateTime.now();

    private OffsetDateTime modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private boolean deleted;

//    @PrePersist
//    public void onPrePersist() {
//        this.createdBy = getUsername();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.modifiedDate = OffsetDateTime.now();
//        this.modifiedBy = getUsername();
//    }

//    private String getUsername() {
//        return ofNullable(SecurityContextHolder.getContext().getAuthentication())
//                .map(Authentication::getName).orElse("anonymous");
//    }

}