------------ CATEGORY SCRIPTS ------------
create table category
(
    id         serial       not null
        constraint category_pk primary key,
    name       varchar(255) not null,
    sort_order integer
);

INSERT INTO category (id, name, sort_order)
VALUES (1, 'Giyim', 1);
INSERT INTO category (id, name, sort_order)
VALUES (2, 'Spor', 2);
INSERT INTO category (id, name, sort_order)
VALUES (3, 'Hobi', 3);
INSERT INTO category (id, name, sort_order)
VALUES (4, 'Sanat', 4);


------------ SUB-CATEGORY SCRIPTS ------------
create table sub_category
(
    id   serial       not null
        constraint sub_category_pk primary key,
    name varchar(255) not null
);

INSERT INTO sub_category (id, name)
VALUES (1, 'Futbol');
INSERT INTO sub_category (id, name)
VALUES (2, 'Basketbol');
INSERT INTO sub_category (id, name)
VALUES (3, 'Tenis');
INSERT INTO sub_category (id, name)
VALUES (4, 'Tablo');

------------ CHILD-CATEGORY SCRIPTS ------------
create table child_category
(
    id   serial       not null
        constraint child_category_pk primary key,
    name varchar(255) not null
);

INSERT INTO child_category (id, name)
VALUES (1, 'Futbol');
INSERT INTO child_category (id, name)
VALUES (2, 'Basketbol');
INSERT INTO child_category (id, name)
VALUES (3, 'Tenis');
INSERT INTO child_category (id, name)
VALUES (4, 'Tablo');

------------CATEGORY-TREE SCRIPTS ------------
create table category_tree
(
    id                serial not null
        constraint category_tree_pk primary key,
    category_id       integer
        constraint ms_product_category_id_fk references category,
    sub_category_id   integer
        constraint ms_product_sub_category_id_fk references sub_category,
    child_category_id integer
        constraint ms_product_child_category_id_fk references child_category
);

-------------------------------------------------------------------------------------------------------------------------

------------ PRODUCT-CATEGORY SCRIPTS ------------
create table product_category
(
    id              serial  not null
        constraint product_category_pk primary key,
    category_id     integer not null
        constraint product_category_category_id_fk references category,
    sub_category_id integer not null
        constraint product_category_sub_category_id_fk references sub_category
);

INSERT INTO product_category (id, category_id, sub_category_id)
VALUES (8, 2, 1);
INSERT INTO product_category (id, category_id, sub_category_id)
VALUES (10, 2, 2);
INSERT INTO product_category (id, category_id, sub_category_id)
VALUES (11, 4, 4);


-------------------------------------------------------------------------------------------------------------------------

------------ STOCK SCRIPTS ------------
create table stock_status
(
    id                serial       not null
        constraint stock_status_pk primary key,
    stock_status_name varchar(255) not null
);

INSERT INTO stock_status (id, stock_status_name)
VALUES (4, 'OUTOFSTOCK');
INSERT INTO stock_status (id, stock_status_name)
VALUES (2, 'LOWSTOCK');
INSERT INTO stock_status (id, stock_status_name)
VALUES (3, 'INSTOCK');

------------ PRODUCT SCRIPTS ------------
create table ms_product
(
    id                  serial not null
        constraint ms_product_pk primary key,
    name                varchar(255),
    price               double precision,
    inventory_status_id integer
        constraint ms_product_stock_status_id_fk references stock_status,
    description         varchar(255),
    category_id         integer
        constraint ms_product_category_id_fk references category,
    quantity            integer,
    rating              integer,
    image               varchar(255),
    sub_category_id     integer
        constraint ms_product_sub_category_id_fk references sub_category
);

INSERT INTO ms_product (id, name, price, inventory_status_id, description, category_id, quantity, rating, image,
                        sub_category_id)
VALUES (11, 'Ayakkabı', 1256.1300048828125, 2, 'Spor Giyim', 1, 1442, 43,
        'https://imgfly.scarabresearch.com/w_300/https://st-vans.mncdn.com/mnresize/1000/1000/Content/media/ProductImg/original/637639359771886229.jpg',
        1);
INSERT INTO ms_product (id, name, price, inventory_status_id, description, category_id, quantity, rating, image,
                        sub_category_id)
VALUES (10, 'Tenis Raketi', 500, 3, 'Tenis ve Tenis Malzemeleri', 2, 92, 2,
        'https://spx.akinoncdn.com/products/2021/12/14/450522/e660983d-44e3-4129-8ff5-3986c9910b1f_size320x320_cropTop.jpg',
        3);

-------------------------------------------------------------------------------------------------------------------------


------------ USER SCRIPTS ------------
create table ms_user
(
    id   varchar(255),
    email   varchar(255),
    name    varchar(255),
    surname varchar(255),
    gender  varchar(255),
    address varchar(255),
    gsm_no  varchar(255),
    date_of_birth varchar(255),
    state   BOOLEAN not null
);

-- INSERT INTO ms_user (id,email, name, surname, gender, address, gsm_no, state)
-- VALUES ('turkuazsengul@gmail.com', 'Turkuaz', 'ŞENGÜL', 'Erkek', 'Test Adres', '05393164759', true);
--
-- INSERT INTO ms_user (email, name, surname, gender, address, gsm_no, state)
-- VALUES ('iremmordk@gmail.com', 'İrem', 'ŞENGÜL', 'Kadın', 'Test Adres 2', '05398431203', true);
-------------------------------------------------------------------------------------------------------------------------

create table test_results
(
    id                           serial       not null
        constraint test_result_pk
            primary key,
    run_time                     varchar(255) not null,
    service_url                  varchar(255) not null,
    request_method_type          varchar(255) not null,
    result_status_code           integer,
    result_status                varchar(255),
    provided_json_response_token varchar(6000),
    expected_json_response_token varchar(6000),
    test_result_status_message   varchar(255)
);

create unique index test_result_service_url_uindex on test_results (service_url);

INSERT INTO test_results (id, run_time, service_url, request_method_type, result_status_code, result_status,
                          provided_json_response_token, expected_json_response_token, test_result_status_message)
VALUES (43, '03.04.2022 18:56:03', 'http://localhost:20000/api/v1/product/category/getAll', 'GET', 200, 'FAIL',
        'eyJyZXR1cm5Db2RlIjoxMTExLCJyZXR1cm5NZXNzYWdlIjoixLDFn2xlbWluaXpCYcWfYXLEsXlsYWdlcsOnZWtsZcWfdGlyaWxtacWfdGlyLiIsInJlc3VsdENvdW50Ijo0LCJyZXR1cm5EYXRhIjpbeyJwa0lkIjoxLCJuYW1lIjoiR2l5aW0ifSx7InBrSWQiOjIsIm5hbWUiOiJTcG9yIn0seyJwa0lkIjozLCJuYW1lIjoiSG9iaSJ9LHsicGtJZCI6NCwibmFtZSI6IlNhbmF0In1dfQ==',
        'eyJyZXR1cm5Db2RlIjoxMTExLCJyZXR1cm5NZXNzYWdlIjoixLDFn2xlbWluaXpCYcWfYXLEsXlsYWdlcsOnZWtsZcWfdGlyaWxtacWfdGlyLiIsInJlc3VsdENvdW50Ijo0LCJyZXR1cm5EYXRhIjpbeyJwa0lkIjoxLCJuYW1lIjoiR2l5aW0ifSx7InBrSWQiOjIsIm5hbWUiOiJTcG9yIn0seyJwa0lkIjozLCJuYW1lIjoiSG9iaSJ9LHsicGtJZCI6NCwibmFtZSI6IlNhbmF0MSJ9XX0=',
        'Data Listesi eşleşmiyor !');
