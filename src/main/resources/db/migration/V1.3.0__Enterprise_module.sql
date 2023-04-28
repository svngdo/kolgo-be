create table if not exists addresses
(
    id      int generated always as identity primary key,
    details text     null,
    city_id smallint null references cities (id) on delete set null
);

create table if not exists enterprise_fields
(
    id   smallint generated always as identity primary key,
    name varchar(255) not null
);

create table if not exists enterprises
(
    id                        int generated always as identity primary key,
    name                      varchar(255),
    tax_identification_number varchar(20),
    address_id                int      null references addresses (id) on delete set null,
    enterprise_field_id       smallint null references enterprise_fields on delete set null,
    user_id                   int      not null references users (id)
);