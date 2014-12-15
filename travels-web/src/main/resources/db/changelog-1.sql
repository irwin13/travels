--liquibase formatted sql

--changeset irwin:create-table-1 dbms:mysql
CREATE TABLE city
(
  id char(32) NOT NULL primary key,
  active char(1) default 'Y',
  create_by varchar(255) default 'SYSTEM',
  create_date timestamp null default null,
  last_update_by varchar(255) default 'SYSTEM',
  last_update_date timestamp null default null,
  last_update_millis BIGINT default 0,
  city_code varchar(255),
  city_name varchar(255)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table city;

--changeset irwin:insert-table-1 dbms:mysql
insert into city (id, city_name, city_code) values ('ee5ad12a765411e4b10f001742076f00', 'JAKARTA', 'JKT');
insert into city (id, city_name, city_code) values ('ee5ad12a765411e4b10f001742076f01', 'DENPASAR', 'DPS');
insert into city (id, city_name, city_code) values ('ee5ad12a765411e4b10f001742076f02', 'SURABAYA', 'SBY');
insert into city (id, city_name, city_code) values ('ee5ad12a765411e4b10f001742076f03', 'SEMARANG', 'SMG');
insert into city (id, city_name, city_code) values ('ee5ad12a765411e4b10f001742076f04', 'PADANG', 'PDG');


