create table if not exists payments
(
    id          int generated always as identity primary key,
    method      varchar(20) not null
        constraint valid_method check ( method in ('MOMO', 'VNPAY')),
    description text        not null,
    amount_paid numeric     not null,
    status      varchar(20) not null
        constraint valid_status check ( status in ('PENDING, CHARGED, REFUNDED') ),
    user_id     int         not null references users (id)
);

create table if not exists bookings
(
    id            int generated always as identity primary key,
    date          date default now(),
    enterprise_id int not null references enterprises (id),
    kol_id        int not null references kols (id),
    payment_id    int not null references payments (id)
);

create table if not exists feedbacks
(
    id         int generated always as identity primary key,
    rating     smallint not null
        constraint valid_rating check ( rating between 1 and 5),
    comment    text,
    user_id    int      not null references users (id),
    booking_id int      not null references bookings (id)
);