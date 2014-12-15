--liquibase formatted sql

--changeset irwin:alter-table-1 dbms:mysql
alter table customer add column customer_password varchar(255);
alter table customer add column last_login_date timestamp null default null;
