package com.edumatch.EduMatch.models;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "projects")
@SQLDelete(sql = "UPDATE projects SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "area_estudio")
    private String studyArea;

    @Column(name = "requerimientos")
    private String requirements;

    @Column(name = "fecha_finalizacion")
    private OffsetDateTime endDate;

    private Boolean isActive;

}
