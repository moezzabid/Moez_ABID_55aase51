CREATE SEQUENCE class_seq AS INTEGER START WITH 1 INCREMENT BY 50;

CREATE TABLE ec_class
(
    id         bigint       NOT NULL PRIMARY KEY,
    name       VARCHAR(50)  NOT NULL UNIQUE,
    id_teacher  BIGINT       NOT NULL,
    created_date TIMESTAMP ,
    created_by VARCHAR(100) ,
    last_modified_by VARCHAR(100),
    last_modified_date TIMESTAMP,

    CONSTRAINT fk_class_teacher FOREIGN KEY (id_teacher) REFERENCES ec_teacher (id)

);