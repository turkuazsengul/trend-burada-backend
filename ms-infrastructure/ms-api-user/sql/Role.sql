------------ ROLE SCRIPTS ------------
create table ms_role
(
    id serial not null constraint role_pk primary key,
    name varchar(255)
);