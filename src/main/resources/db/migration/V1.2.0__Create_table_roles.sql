create table if not exists roles
(
    id   smallint generated always as identity primary key,
    name varchar(20) not null unique
);