--CREATE ROLES
INSERT INTO `roles` (`id`, `created_by`, `created_date`, `deleted`, `description`, `modified_by`, `modified_date`, `name`)
VALUES ('1', 'admin', '2023-10-07 01:11:02.000000', b'0', 'admin', 'admin', '2023-10-07 01:11:02.000000', 'admin');

INSERT INTO `projects` (`id`, `created_by`, `created_date`, `deleted`, `modified_by`, `modified_date`, `descripcion`, `fecha_finalizacion`, `is_active`, `nombre`, `requerimientos`, `area_estudio`)
VALUES ('1', 'danito', '2023-10-08 05:15:55.000000', b'0', 'danito', '2023-10-08 05:15:55.000000', 'probando', '2023-10-18 05:15:55.000000', b'0', 'edumatch', 'requerimientos en tramite', 'desarrollo');