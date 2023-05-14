create table if not exists enterprises
(
    id         int generated always as identity primary key,
    user_id    int          not null references users (id),
    name       varchar(255) null,
    phone      varchar(20)  null,
    tax_id     varchar(20)  null,
    address_id int          null references addresses (id) on delete set null
);

create table if not exists enterprise_fields
(
    enterprise_id int      not null references enterprises (id),
    field_id      smallint not null references fields (id),
    primary key (enterprise_id, field_id)
);