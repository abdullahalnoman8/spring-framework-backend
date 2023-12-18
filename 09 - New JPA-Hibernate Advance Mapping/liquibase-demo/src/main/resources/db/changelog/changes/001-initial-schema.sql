--liquibase formatted sql

--changeset abdullah:1

create table person (id integer not null auto_increment,
                     address varchar(255),
                     name varchar(255),
                     primary key (id))
    engine=InnoDB;


-- rollback drop table person