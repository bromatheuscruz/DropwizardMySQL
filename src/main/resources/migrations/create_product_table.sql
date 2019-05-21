--liquibase formatted sql

--changeset iocruz:1
create table product (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80),
    description VARCHAR(120)
);
