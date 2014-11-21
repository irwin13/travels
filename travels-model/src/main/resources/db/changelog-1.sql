--liquibase formatted sql

--changeset irwin:1 dbms:postgresql
CREATE OR REPLACE FUNCTION public.gen_uuid() RETURNS character varying AS
'select md5(clock_timestamp()::text||random()::text)::character varying'
  LANGUAGE sql VOLATILE
  COST 100;

CREATE TABLE public.city
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  last_update_millis bigint,
  city_code character varying(255),
  city_name character varying(255),
  CONSTRAINT city_pk PRIMARY KEY (id)
);

