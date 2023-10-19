package com.edumatch.EduMatch.models;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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
public class RoleEntity extends AuditableEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
