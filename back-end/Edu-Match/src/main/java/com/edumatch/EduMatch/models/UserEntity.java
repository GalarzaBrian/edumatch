package com.edumatch.EduMatch.models;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Builder
@Entity

public class UserEntity extends AuditableEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "numero_documento")
    private Long id;

    @NotNull
    @Column(name = "nombre")
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(name = "correo")
    private String email;

    @NotNull
    @Column(name = "contrasena")
    private String password;

    @Column(name = "fecha_nacimiento")
    private OffsetDateTime birthday;

    @NotNull
    private String photo;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id")
    )
    private Collection<RoleEntity> roles;
}