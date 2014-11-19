--liquibase formatted sql

--changeset irwin:3
INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(2, 2, 1, '2014-10-10', '10:00:00', '2014-10-13', '13:00:00', 2000);

INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(1, 1, 2, '2014-10-13', '13:00:00', '2014-10-15', '07:00:00', 5000);

INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(3, 2, 3, '2014-11-03', '13:00:00', '2014-10-15', '07:00:00', 90000);

INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(2, 5, 1, '2014-11-30', '13:00:00', '2014-10-15', '07:00:00', 77000);

INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(1, 4, 5, '2014-11-21', '13:00:00', '2014-10-15', '07:00:00', 3000);

INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(1, 2, 3, '2014-11-17', '13:00:00', '2014-10-15', '07:00:00', 13000);

INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(1, 4, 1, '2014-12-02', '13:00:00', '2014-10-15', '07:00:00', 8000);

INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(4, 1, 5, '2014-12-05', '13:00:00', '2014-10-15', '07:00:00', 17000);

INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(4, 4, 3, '2014-11-28', '13:00:00', '2014-10-15', '07:00:00', 88000);

INSERT INTO airline_ticket
(airline_id, from_city, destination_city, landing_date, landing_time, arrival_date, arrival_time, price)
VALUES(4, 5, 2, '2014-11-28', '13:00:00', '2014-10-15', '07:00:00', 99000);
