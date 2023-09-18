------------ USER SCRIPTS ------------
create table ms_user
(
    id serial not null constraint user_pk primary key,
    email varchar(255),
    password varchar(255),
    name varchar(255),
    surname varchar(255),
    gender varchar(255),
    address varchar(255),
    gsm_no varchar(255),
    confirm_code_created_time DATE,
    state BOOLEAN not null,
    confirm_code integer
);


create table ms_user_role
(
    id serial not null constraint user_role_pk primary key,
    user_id integer constraint fk_user_id references ms_user,
    role_id integer constraint fk_role_id references ms_role
);
