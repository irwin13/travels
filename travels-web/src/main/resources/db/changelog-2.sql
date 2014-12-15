--liquibase formatted sql

--changeset irwin:create-table-2 dbms:mysql
create table airline (
    id char(32) NOT NULL primary key,
    active char(1) default 'Y',
    create_by varchar(255) default 'SYSTEM',
    create_date timestamp null default null,
    last_update_by varchar(255) default 'SYSTEM',
    last_update_date timestamp null default null,
    last_update_millis BIGINT default 0,
    airline_name varchar(100),
    airline_code varchar(100)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table airline;

--changeset irwin:create-table-3 dbms:mysql
create table airport (
    id char(32) NOT NULL primary key,
    active char(1) default 'Y',
    create_by varchar(255) default 'SYSTEM',
    create_date timestamp null default null,
    last_update_by varchar(255) default 'SYSTEM',
    last_update_date timestamp null default null,
    last_update_millis BIGINT default 0,
    airport_name varchar(100),
    airport_code varchar(100),
    city char(32),
    airport_address varchar(100)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table airport;

--changeset irwin:create-table-4 dbms:mysql
create table customer_ticket (
    id char(32) NOT NULL primary key,
    active char(1) default 'Y',
    create_by varchar(255) default 'SYSTEM',
    create_date timestamp null default null,
    last_update_by varchar(255) default 'SYSTEM',
    last_update_date timestamp null default null,
    last_update_millis BIGINT default 0,
    customer_name varchar(100),
    customer_phone varchar(100),
    customer_email varchar(100),
    airline_ticket char(32)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table customer_ticket;

--changeset irwin:create-table-5 dbms:mysql
create table airline_ticket (
    id char(32) NOT NULL primary key,
    active char(1) default 'Y',
    create_by varchar(255) default 'SYSTEM',
    create_date timestamp null default null,
    last_update_by varchar(255) default 'SYSTEM',
    last_update_date timestamp null default null,
    last_update_millis BIGINT default 0,
    airline char(32),
    from_city char(32),
    destination_city char(32),
    landing_date date,
    landing_time time,
    arrival_date date,
    arrival_time time,
    price double
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table airline_ticket;

--changeset irwin:insert-table-2 dbms:mysql
insert into airline (id, airline_name, airline_code) values ('ff5ad12a765411e4b10f001742076f00', 'GARUDA INDONESIA',
'GIA');
insert into airline (id, airline_name, airline_code) values ('ff5ad12a765411e4b10f001742076f01', 'AIR ASIA', 'AWQ');
insert into airline (id, airline_name, airline_code) values ('ff5ad12a765411e4b10f001742076f02', 'MERPATI', 'MNA');
insert into airline (id, airline_name, airline_code) values ('ff5ad12a765411e4b10f001742076f03', 'SRIWIJAYA AIR',
'SJY');

--changeset irwin:insert-table-3 dbms:mysql
INSERT INTO airline_ticket
(id,
airline,
from_city,
destination_city,
landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(REPLACE(uuid(), '-', ''),
'ff5ad12a765411e4b10f001742076f00',
'ee5ad12a765411e4b10f001742076f00',
'ee5ad12a765411e4b10f001742076f01',
'2014-10-10', '10:00:00', '2014-10-13', '13:00:00', 2000);

INSERT INTO airline_ticket
(id,
airline,
from_city,
destination_city,
landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(REPLACE(uuid(), '-', ''),
'ff5ad12a765411e4b10f001742076f01',
'ee5ad12a765411e4b10f001742076f01',
'ee5ad12a765411e4b10f001742076f02',
'2014-10-13', '13:00:00', '2014-10-15', '07:00:00', 5000);

INSERT INTO airline_ticket
(id,
airline,
from_city,
destination_city,
landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(REPLACE(uuid(), '-', ''),
'ff5ad12a765411e4b10f001742076f02',
'ee5ad12a765411e4b10f001742076f02',
'ee5ad12a765411e4b10f001742076f03',
'2014-11-03', '13:00:00', '2014-10-15', '07:00:00', 90000);

INSERT INTO airline_ticket
(id,
airline,
from_city,
destination_city,
landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(REPLACE(uuid(), '-', ''),
'ff5ad12a765411e4b10f001742076f03',
'ee5ad12a765411e4b10f001742076f03',
'ee5ad12a765411e4b10f001742076f04',
'2014-11-30', '13:00:00', '2014-10-15', '07:00:00', 77000);

INSERT INTO airline_ticket
(id,
airline,
from_city,
destination_city,
landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(REPLACE(uuid(), '-', ''),
'ff5ad12a765411e4b10f001742076f00',
'ee5ad12a765411e4b10f001742076f04',
'ee5ad12a765411e4b10f001742076f00',
'2014-11-21', '13:00:00', '2014-10-15', '07:00:00', 3000);

INSERT INTO airline_ticket
(id,
airline,
from_city,
destination_city,
landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(REPLACE(uuid(), '-', ''),
'ff5ad12a765411e4b10f001742076f01',
'ee5ad12a765411e4b10f001742076f03',
'ee5ad12a765411e4b10f001742076f01',
'2014-11-17', '13:00:00', '2014-10-15', '07:00:00', 13000);

INSERT INTO airline_ticket
(id,
airline,
from_city,
destination_city,
landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(REPLACE(uuid(), '-', ''),
'ff5ad12a765411e4b10f001742076f02',
'ee5ad12a765411e4b10f001742076f02',
'ee5ad12a765411e4b10f001742076f04',
'2014-12-02', '13:00:00', '2014-10-15', '07:00:00', 8000);