package com.edumatch.EduMatch.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "projects",
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
    })
    @JsonIgnore
    private Set<UserEntity> users = new HashSet<>();

    @Override
    public void onPrePersist() {
        this.endDate= OffsetDateTime.now().plus(15, ChronoUnit.DAYS);
        super.onPrePersist();
    }
    public void addUser(UserEntity user) {
        this.users.add(user);
        user.getProjects().add(this);
    }
    public void removeUser(long userId) {
        UserEntity tag = this.users.stream().filter(t -> t.getId() == userId).findFirst().orElse(null);
        if (tag != null) {
            this.users.remove(tag);
            tag.getProjects().remove(this);
        }
    }

}
