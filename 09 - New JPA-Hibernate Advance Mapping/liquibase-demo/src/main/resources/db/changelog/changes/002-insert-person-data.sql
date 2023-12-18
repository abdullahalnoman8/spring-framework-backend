--liquibase formatted sql

--changeset abdullah:2
INSERT INTO person(id,name,address)
VALUES ( 1,'John Doe','Germany')