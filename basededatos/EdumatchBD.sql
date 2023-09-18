
CREATE DATABASE Edumatch;

-- Utilizar la base de datos
USE Edumatch;

CREATE TABLE Usuarios (
    numero_documento VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    -- Puedes agregar más campos de perfil aquí (por ejemplo, habilidades)
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE Proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proyecto VARCHAR(255) NOT NULL,
    descripcion TEXT,
    area_estudio VARCHAR(255),
    requisitos TEXT,
    fecha_inicio DATE,
    fecha_finalizacion DATE,
    usuario_id VARCHAR(20),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(numero_documento),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear la tabla de Conexiones (Matches)
CREATE TABLE Conexiones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id INT,
    usuario_id VARCHAR(20),
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(numero_documento),
    fecha_conexion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


