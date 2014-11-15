--liquibase formatted sql

--changeset irwin:1 dbms:mysql
create table test1 (
    id int auto_increment primary key,
    name varchar(100)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table test1;

--changeset irwin:2
insert into test1 (name) values ('orang1');
insert into test1 (name) values ('orang2');