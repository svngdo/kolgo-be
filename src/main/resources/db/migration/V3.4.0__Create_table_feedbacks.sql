create table if not exists feedbacks
(
    id            int generated always as identity primary key,
    rating        smallint,
    comment       text,
    enterprise_id int not null references enterprises (id),
    kol_id        int not null references kols (id),
    booking_id    int not null references bookings (id)
);
