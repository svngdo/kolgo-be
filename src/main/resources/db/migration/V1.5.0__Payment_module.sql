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
    amount      numeric      not null    default 0,
    description text         not null,
    date        timestamp with time zone default now(),
    status      varchar(20)  not null
        constraint valid_payment_status check ( status in ('FAILED, SUCCESS, REFUNDED') ),
    sender_id   int          not null references users (id),
    receiver_id int          not null references users (id)
);

create table if not exists payment_users
(
    payment_id int not null references payments (id),
    user_id    int not null references users (id),
    primary key (payment_id, user_id)
)