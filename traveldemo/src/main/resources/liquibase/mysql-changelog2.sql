--liquibase formatted sql

--changeset irwin:3 dbms:mysql
create table city (
    id int auto_increment primary key,
    city_name varchar(100),
    city_code varchar(100)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table city;

--changeset irwin:4
insert into city (city_name, city_code) values ('JAKARTA', 'SBY');
insert into city (city_name, city_code) values ('DENPASAR', 'DPS');
insert into city (city_name, city_code) values ('SURABAYA', 'SBY');
insert into city (city_name, city_code) values ('SEMARANG', 'SMG');
insert into city (city_name, city_code) values ('PADANG', 'PDG');

--changeset irwin:5 dbms:mysql
create table airline (
    id int auto_increment primary key,
    airline_name varchar(100),
    airline_code varchar(100)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table airline;

--changeset irwin:6
insert into airline (airline_name, airline_code) values ('GARUDA INDONESIA', 'GIA');
insert into airline (airline_name, airline_code) values ('AIR ASIA', 'AWQ');
insert into airline (airline_name, airline_code) values ('MERPATI', 'MNA');
insert into airline (airline_name, airline_code) values ('SRIWIJAYA AIR', 'SJY');

--changeset irwin:7 dbms:mysql
create table airport (
    id int auto_increment primary key,
    airport_name varchar(100),
    airport_code varchar(100),
    city int,
    airport_address varchar(100)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table airport;

--changeset irwin:8 dbms:mysql
create table customer_ticket (
    id int auto_increment primary key,
    customer_name varchar(100),
    customer_phone varchar(100),
    customer_email varchar(100),
    airline_ticket int
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table customer_ticket;

--changeset irwin:9 dbms:mysql
create table airline_ticket (
    id int auto_increment primary key,
    airline_id int,
    from_city int,
    destination_city int,
    landing_date date,
    landing_time time,
    arrival_date date,
    arrival_time time,
    price int
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
--rollback drop table airline_ticket;