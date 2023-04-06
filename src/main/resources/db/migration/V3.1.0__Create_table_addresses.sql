create table if not exists addresses
(
    id              int generated always as identity primary key,
    building_number varchar(20),
    street_name     varchar(50),
    ward            varchar(50),
    district        varchar(50),
    city_id         smallint null references cities (id) on delete set null
);
