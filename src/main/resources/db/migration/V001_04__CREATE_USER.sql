CREATE SEQUENCE user_seq AS INTEGER START WITH 1 INCREMENT BY 50;

CREATE TABLE ec_user
(
    id         bigint       NOT NULL PRIMARY KEY,
    username VARCHAR(20)  NOT NULL UNIQUE,
    email VARCHAR(50)   NOT NULL UNIQUE,
    password  VARCHAR(120)  NOT NULL UNIQUE
);

CREATE TABLE ec_role
(
    id         bigint       NOT NULL PRIMARY KEY,
    name VARCHAR(50)  NOT NULL UNIQUE
);

CREATE TABLE ec_user_role
(
    id_user  BIGINT       NOT NULL,
    id_role  BIGINT       NOT NULL,

    CONSTRAINT fk_user_role FOREIGN KEY (id_user) REFERENCES ec_user (id),
    CONSTRAINT fk_role_user FOREIGN KEY (id_role) REFERENCES ec_role (id)
);

INSERT INTO ec_role (id,name)
 VALUES
 (1, 'ROLE_USER'),
 (2, 'ROLE_ADMIN')