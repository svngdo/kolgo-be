create table if not exists users
(
    id         int generated always as identity primary key,
    email      varchar(50)  not null unique,
    password   varchar(255) not null,
    first_name varchar(20)  not null,
    last_name  varchar(20)  not null,
    phone      varchar(20)  null,
    avatar     varchar(255) null,
    role       varchar(20)  not null
);

create table if not exists tokens
(
    id         int generated always as identity primary key,
    value      varchar   not null,
    expires_in timestamp not null default current_timestamp,
    user_id    int       not null references users (id)
);