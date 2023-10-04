--CREATE ROLES
INSERT INTO roles
    (id, "name", description)
VALUES (1, 'ROLE_USER', 'user role'),
       (2, 'ROLE_ADMIN', 'admin role');

--CREATE 20 USERS/ADMINS

INSERT INTO users
    (first_name, last_name, email, password, photo)

VALUES ('admin1', 'admin1', 'admin1@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin1.jpg'),
       ('admin2', 'admin2', 'admin2@email.com', '$2a$10$CVXX64MTPSQ3E6Wgscm2QOwEGbaA.RDqDf9TLHJaGimAde8t2eaZS',
        'src/img/admin2.jpg');
INSERT INTO user_role
    (user_id, role_id)
values (1, 2),
        ;