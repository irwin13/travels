--liquibase formatted sql

--changeset irwin:insert-table-1 dbms:mysql
INSERT INTO airline_ticket
(id,
airline,
from_city,
destination_city,
landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(REPLACE(uuid(), '-', ''),
'ff5ad12a765411e4b10f001742076f02',
'ee5ad12a765411e4b10f001742076f01',
'ee5ad12a765411e4b10f001742076f00',
'2014-12-31', '13:00:00', '2014-12-31', '17:00:00', 10000);

--changeset irwin:update-table-1 dbms:mysql
update airline_ticket set last_update_date = current_timestamp();
update airline set last_update_date = current_timestamp();
update city set last_update_date = current_timestamp();
update customer_ticket set last_update_date = current_timestamp();
update airport set last_update_date = current_timestamp();

