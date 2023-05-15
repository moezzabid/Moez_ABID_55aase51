CREATE SEQUENCE student_seq AS INTEGER START WITH 1 INCREMENT BY 50;

CREATE TABLE ec_student
(
    id         bigint       NOT NULL PRIMARY KEY,
    first_name VARCHAR(50)  NOT NULL UNIQUE,
    last_name  VARCHAR(50)  NOT NULL UNIQUE,
    id_classe  BIGINT       NOT NULL,
    created_date TIMESTAMP ,
    created_by VARCHAR(100) ,
    last_modified_by VARCHAR(100),
    last_modified_date TIMESTAMP,

    CONSTRAINT fk_student_class FOREIGN KEY (id_classe) REFERENCES ec_class (id)

);