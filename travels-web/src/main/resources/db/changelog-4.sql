--liquibase formatted sql

--changeset irwin:alter-table-1 dbms:mysql
alter table airline add column logo_img varchar(255);

--changeset irwin:update-table-1 dbms:mysql
update airline set logo_img = 'garuda.jpg' where id = 'ff5ad12a765411e4b10f001742076f00';
update airline set logo_img = 'airasia.jpg' where id = 'ff5ad12a765411e4b10f001742076f01';
update airline set logo_img = 'merpati.jpg' where id = 'ff5ad12a765411e4b10f001742076f02';
update airline set logo_img = 'sriwijayaair.png' where id = 'ff5ad12a765411e4b10f001742076f03';