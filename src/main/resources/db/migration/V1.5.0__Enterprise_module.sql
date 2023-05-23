create table if not exists enterprises
(
    id              int generated always as identity primary key,
    user_id         int          not null references users (id),
    name            varchar(255) null,
    phone           varchar(20)  null,
    introduction    text         null,
    tax_id          varchar(20)  null,
    city_id         int          null references cities (id) on delete set null,
    address_details varchar(255) null
);

create table if not exists enterprise_fields
(
    enterprise_id int      not null references enterprises (id),
    field_id      smallint not null references fields (id),
    primary key (enterprise_id, field_id)
);