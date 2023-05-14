create table if not exists cities
(
    id   smallint generated by default as identity primary key,
    name varchar(50) not null,
    code varchar(10) not null
);

create table if not exists addresses
(
    id      int generated always as identity primary key,
    city_id smallint     null references cities (id) on delete set null,
    details varchar(255) null
);

create table if not exists fields
(
    id   smallint generated by default as identity primary key,
    name varchar(255) not null,
    type varchar(10)  not null
        constraint valid_field_type check ( type in ('KOL', 'ENTERPRISE'))
);
