create table if not exists bookings
(
    id            int generated always as identity primary key,
    time          timestamp with time zone default now(),
    cost          int not null,
    enterprise_id int not null references enterprises (id),
    kol_id        int not null references kols (id)
);
