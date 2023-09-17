------------ PRODUCT SCRIPTS ------------
create table ms_product
(
    id                  serial not null
        constraint ms_product_pk
            primary key,
    name                varchar(255),
    price               double precision,
    inventory_status_id integer
        constraint ms_product_stock_status_id_fk
            references stock_status,
    description         varchar(255),
    category_id         integer
        constraint ms_product_category_id_fk
            references category,
    quantity            integer,
    rating              integer,
    image               varchar(255),
    sub_category_id     integer
        constraint ms_product_sub_category_id_fk
            references sub_category
);

alter table ms_product
    owner to postgres;

INSERT INTO public.ms_product (id, name, price, inventory_status_id, description, category_id, quantity, rating, image,
                               sub_category_id)
VALUES (11, 'Ayakkabı', 1256.1300048828125, 2, 'Spor Giyim', 1, 1442, 43,
        'https://imgfly.scarabresearch.com/w_300/https://st-vans.mncdn.com/mnresize/1000/1000/Content/media/ProductImg/original/637639359771886229.jpg',
        1);
INSERT INTO public.ms_product (id, name, price, inventory_status_id, description, category_id, quantity, rating, image,
                               sub_category_id)
VALUES (10, 'Tenis Raketi', 500, 3, 'Tenis ve Tenis Malzemeleri', 2, 92, 2,
        'https://spx.akinoncdn.com/products/2021/12/14/450522/e660983d-44e3-4129-8ff5-3986c9910b1f_size320x320_cropTop.jpg',
        3);



------------ PRODUCT-CATEGORY SCRIPTS ------------
create table product_category
(
    id              serial  not null
        constraint product_category_pk
            primary key,
    category_id     integer not null
        constraint product_category_category_id_fk
            references category,
    sub_category_id integer not null
        constraint product_category_sub_category_id_fk
            references sub_category
);

alter table product_category
    owner to postgres;

INSERT INTO public.product_category (id, category_id, sub_category_id) VALUES (8, 2, 1);
INSERT INTO public.product_category (id, category_id, sub_category_id) VALUES (10, 2, 2);
INSERT INTO public.product_category (id, category_id, sub_category_id) VALUES (11, 4, 4);