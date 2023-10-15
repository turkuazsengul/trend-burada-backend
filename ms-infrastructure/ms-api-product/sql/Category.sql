------------ CATEGORY SCRIPTS ------------
create table category
(
    id serial not null constraint category_pk primary key,
    name varchar(255) not null,
    sort_order integer
);

alter table category owner to postgres;

INSERT INTO public.category (id, name, sort_order) VALUES (1, 'Giyim', 1);
INSERT INTO public.category (id, name, sort_order) VALUES (2, 'Spor', 2);
INSERT INTO public.category (id, name, sort_order) VALUES (3, 'Hobi', 3);
INSERT INTO public.category (id, name, sort_order) VALUES (4, 'Sanat', 4);


------------ SUB-CATEGORY SCRIPTS ------------
create table sub_category
(
    id serial not null constraint sub_category_pk primary key,
    name varchar(255) not null
);

alter table sub_category owner to postgres;

INSERT INTO public.sub_category (id, name) VALUES (1, 'Futbol');
INSERT INTO public.sub_category (id, name) VALUES (2, 'Basketbol');
INSERT INTO public.sub_category (id, name) VALUES (3, 'Tenis');
INSERT INTO public.sub_category (id, name) VALUES (4, 'Tablo');

------------ CHILD-CATEGORY SCRIPTS ------------
create table child_category
(
    id serial not null constraint child_category_pk primary key,
    name varchar(255) not null
);

alter table sub_category owner to postgres;

INSERT INTO public.sub_category (id, name) VALUES (1, 'Futbol');
INSERT INTO public.sub_category (id, name) VALUES (2, 'Basketbol');
INSERT INTO public.sub_category (id, name) VALUES (3, 'Tenis');
INSERT INTO public.sub_category (id, name) VALUES (4, 'Tablo');

------------CATEGORY-TREE SCRIPTS ------------
create table category_tree
(
    id serial not null constraint category_tree_pk primary key,
    category_id integer constraint ms_product_category_id_fk references category,
    sub_category_id integer constraint ms_product_sub_category_id_fk references sub_category,
    child_category_id integer constraint ms_product_child_category_id_fk references child_category
);

alter table sub_category owner to postgres;