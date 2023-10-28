--CREATE ROLES
INSERT INTO roles (id, created_by, created_date, deleted, description, modified_by, modified_date, name)
VALUES (1, 'admin', '2023-10-07 01:11:02.000000', false, 'admin', 'admin', '2023-10-07 01:11:02.000000', 'admin');

INSERT INTO projects (id, created_by, created_date, deleted, modified_by, modified_date, descripcion, fecha_finalizacion, is_active, nombre, requerimientos, area_estudio)
VALUES (1, 'danito', '2023-10-08 05:15:55.000000', false, 'danito', '2023-10-08 05:15:55.000000', 'probando', '2023-10-18 05:15:55.000000', false, 'edumatch', 'requerimientos en tramite', 'desarrollo');

INSERT INTO `users` (`id`, `created_by`, `created_date`, `deleted`, `modified_by`, `modified_date`, `fecha_nacimiento`, `dni`, `correo`, `nombre`, `apellido`, `contrasena`, `foto_perfil`)
VALUES ('1', 'Admin', '2023-10-09 21:51:30.000000', b'0', 'Admin', '2023-10-09 21:51:31.000000', '2023-10-03 21:51:31.000000', '32923000', 'admin@mail.com', NULL, NULL, 'password', NULL);

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO user_project (user_id, project_id) VALUES (1, 1);
