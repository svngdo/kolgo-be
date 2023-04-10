create table if not exists users
(
    id           int generated always as identity primary key,
    email        varchar(50)  not null unique,
    password     varchar(255) not null,
    first_name   varchar(20)  not null,
    last_name    varchar(20)  not null,
    phone_number varchar(20)  null,
    avatar       varchar      null
);