------------ STOCK SCRIPTS ------------
create table stock_status
(
    id                serial       not null
        constraint stock_status_pk
            primary key,
    stock_status_name varchar(255) not null
);

alter table stock_status
    owner to postgres;

INSERT INTO public.stock_status (id, stock_status_name) VALUES (4, 'OUTOFSTOCK');
INSERT INTO public.stock_status (id, stock_status_name) VALUES (2, 'LOWSTOCK');
INSERT INTO public.stock_status (id, stock_status_name) VALUES (3, 'INSTOCK');