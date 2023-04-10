create table if not exists payments
(
    id            int generated always as identity primary key,
    amount        int          not null,
    reference     varchar(255) not null unique,
    kol_id        int          not null references kols (id),
    enterprise_id int          not null references enterprises (id)
)
