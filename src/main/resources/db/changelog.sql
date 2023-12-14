--liquibase formatted sql

--changeset Lizogubov Eugeny:init_schema
create table USERS
(
    ID         INTEGER auto_increment primary key,
    NAME       CHARACTER VARYING(128)        not null,
    EMAIL      CHARACTER VARYING(128) unique not null,
    PASSWORD   CHARACTER VARYING(128)        not null,
    REGISTERED TIMESTAMP default NOW()       not null,
    ROLE       CHARACTER VARYING(255)
);

create table HARD_DRIVE
(
    AMOUNT_IN_STOCK INTEGER                not null,
    CAPACITY        INTEGER                not null,
    ID              INTEGER auto_increment primary key,
    PRICE           INTEGER                not null,
    SERIES_NUMBER   INTEGER                not null,
    MANUFACTURER    CHARACTER VARYING(128) not null,
    check ("AMOUNT_IN_STOCK" >= 0),
    check ("CAPACITY" >= 0),
    check ("PRICE" >= 1)
);

create table LAPTOP
(
    AMOUNT_IN_STOCK INTEGER                not null,
    ID              INTEGER auto_increment primary key,
    PRICE           INTEGER                not null,
    SERIES_NUMBER   INTEGER                not null,
    SIZE            INTEGER,
    MANUFACTURER    CHARACTER VARYING(128) not null,
    check ("AMOUNT_IN_STOCK" >= 0),
    check ("PRICE" >= 1)
);

create table MONITOR
(
    AMOUNT_IN_STOCK INTEGER                not null,
    ID              INTEGER auto_increment primary key,
    PRICE           INTEGER                not null,
    SCREEN_SIZE     INTEGER                not null,
    SERIES_NUMBER   INTEGER                not null,
    MANUFACTURER    CHARACTER VARYING(128) not null,
    check ("AMOUNT_IN_STOCK" >= 0),
    check ("PRICE" >= 1),
    check ("SCREEN_SIZE" >= 0)
);

create table PERSONAL_COMPUTER
(
    AMOUNT_IN_STOCK INTEGER                not null,
    ID              INTEGER auto_increment primary key,
    PRICE           INTEGER                not null,
    SERIES_NUMBER   INTEGER                not null,
    MANUFACTURER    CHARACTER VARYING(128) not null,
    FORM_FACTOR     CHARACTER VARYING(255) not null,
    check ("AMOUNT_IN_STOCK" >= 0),
    check ("PRICE" >= 1),
    check ("FORM_FACTOR" IN ('DESKTOP', 'NETTOP', 'MONOBLOCK'))
);

--changeset Lizogubov Eugeny:populate_data
-- INSERT INTO USERS (NAME, EMAIL, PASSWORD, ROLE)
-- VALUES ('user', 'user@gmail.com', 'password', 'USER'),
--        ('admin', 'admin@gmail.com', 'password', 'ADMIN');

INSERT INTO PERSONAL_COMPUTER (SERIES_NUMBER, MANUFACTURER, PRICE, AMOUNT_IN_STOCK, FORM_FACTOR)
VALUES (1000, 'HP', 100, 10, 'DESKTOP'),
       (2000, 'Asus', 200, 20, 'NETTOP'),
       (3000, 'Apple', 300, 30, 'MONOBLOCK');

INSERT INTO HARD_DRIVE (SERIES_NUMBER, MANUFACTURER, PRICE, AMOUNT_IN_STOCK, CAPACITY)
VALUES (4000, 'Samsung', 50, 5, 256),
       (5000, 'Seagate', 60, 6, 512),
       (6000, 'Western Digital', 70, 7, 1024);

INSERT INTO LAPTOP (SERIES_NUMBER, MANUFACTURER, PRICE, AMOUNT_IN_STOCK, SIZE)
VALUES (7000, 'Asus', 1000, 8, 15),
       (8000, 'MSI', 950, 9, 17),
       (9000, 'Lenovo', 750, 10, 14);

INSERT INTO MONITOR (SERIES_NUMBER, MANUFACTURER, PRICE, AMOUNT_IN_STOCK, SCREEN_SIZE)
VALUES (10000, 'LG', 1100, 13, 27),
       (11000, 'Dell', 1200, 14, 24),
       (12000, 'ASUS', '1300', 15, 32);