package com.edumatch.EduMatch.Models;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE roles SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class RoleEntity {

    private static final long serialVersionUID = 1L;

    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private String description;

    private OffsetDateTime createdDate;

    private String createdBy;

    private OffsetDateTime modifiedDate;

    private String modifiedBy;

    private boolean deleted;
}
