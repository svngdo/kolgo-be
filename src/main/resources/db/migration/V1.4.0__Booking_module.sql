create table if not exists payments
(
    id          int generated always as identity primary key,
    method      varchar(20)  not null
        constraint valid_payment_method check ( method in ('MOMO', 'VNPAY')),
    txn_ref     varchar(100) not null,
    txn_no      varchar(100) not null,
    bank_txn_no varchar(100) not null,
    bank_code   varchar(20)  not null
        constraint valid_bank_code check ( bank_code in ('VNPAYQR', 'VNBANK', 'INTCARD')),
    amount      numeric      not null,
    description text         not null,
    date        timestamp with time zone default now(),
    status      varchar(20)  not null
        constraint valid_payment_status check ( status in ('FAILED, SUCCESS, REFUNDED') ),
    sender_id   int          not null references users (id),
    receiver_id int          not null references users (id)
);

create table if not exists bookings
(
    id         int generated always as identity primary key,
    date       timestamp with time zone default now(),
    status     varchar(20) not null
        constraint valid_booking_status check ( status in ('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELED')),
    user_id    int         not null references users (id),
    kol_id     int         not null references kols (id),
    payment_id int         not null references payments (id)
);

create table if not exists feedbacks
(
    id         int generated always as identity primary key,
    rating     smallint not null
        constraint valid_feedback_rating check ( rating between 1 and 5),
    comment    text,
    user_id    int      not null references users (id),
    kol_id     int      not null references kols (id),
    booking_id int      not null references bookings (id)
);