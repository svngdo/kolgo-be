create table if not exists bookings
(
    id            int generated always as identity primary key,
    time          date not null default current_date,
    enterprise_id int  not null references enterprises (id),
    kol_id        int  not null references kols (id)
);
