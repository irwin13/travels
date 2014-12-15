--liquibase formatted sql

--changeset irwin:create-table-3 dbms:mysql
create table customer (
    id char(32) NOT NULL primary key,
    active char(1) default 'Y',
    create_by varchar(255) default 'SYSTEM',
    create_date timestamp null default null,
    last_update_by varchar(255) default 'SYSTEM',
    last_update_date timestamp null default null,
    last_update_millis BIGINT default 0,
    customer_name varchar(255),
    customer_phone varchar(100),
    customer_email varchar(255),
    customer_address varchar(255)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table airport;

--changeset irwin:alter-table-1 dbms:mysql
alter table customer_ticket add column customer char(32);
alter table customer_ticket add column passenger_title varchar(255);
alter table customer_ticket add column passenger_name varchar(255);
alter table customer_ticket add column luggage int;
alter table customer_ticket add column payment_method varchar(255);
alter table customer_ticket add column payment_status varchar(255);