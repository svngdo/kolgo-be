create table if not exists enterprises
(
    id         int generated always as identity primary key,
    user_id    int          not null references users (id),
    name       varchar(255) null,
    phone      varchar(20)  null,
    tax_id     varchar(20)  null,
    address_id int          not null references addresses (id) on delete set null,
    field_id   smallint     null references fields (id) on delete set null
);