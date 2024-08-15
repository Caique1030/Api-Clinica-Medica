CREATE TABLE Usuarios (
    id BIGSERIAL NOT NULL,
    login varchar(180) NOT NULL,
    senha varchar(255) NOT NULL,

    PRIMARY KEY (id)
);