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

create table if not exists roles
(
    id   smallint generated always as identity primary key,
    name varchar(20) not null unique
);

create table if not exists user_roles
(
    user_id int      not null references users (id),
    role_id smallint not null references roles (id),
    primary key (user_id, role_id)
);

create table if not exists tokens
(
    id         int generated always as identity primary key,
    value      varchar   not null,
    expires_in timestamp not null default current_timestamp,
    user_id    int       not null references users (id)
);