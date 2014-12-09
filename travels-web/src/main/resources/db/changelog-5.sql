--liquibase formatted sql

--changeset irwin:alter-table-1 dbms:mysql
alter table customer_ticket add column payment_method varchar(255);
alter table customer_ticket add column payment_status varchar(255);
