create table if not exists bookings
(
    id           int generated always as identity primary key,
    post_price   numeric     not null     default 0,
    post_number  int         not null     default 0,
    video_price  numeric     not null     default 0,
    video_number int         not null     default 0,
    total_price  numeric     not null     default 0,
    description  text        not null,
    timestamp    timestamp with time zone default now(),
    status       varchar(20) not null
        constraint valid_booking_status check ( status in ('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELED')),
    user_id      int         not null references users (id),
    kol_id       int         not null references kols (id),
    payment_id   int         null references payments (id)
);

create table if not exists booking_users
(
    booking_id int not null references bookings (id),
    user_id    int not null references users (id),
    primary key (booking_id, user_id)
);

create table if not exists feedbacks
(
    id          int generated always as identity primary key,
    rating      smallint not null
        constraint valid_feedback_rating check ( rating between 1 and 5),
    comment     text,
    sender_id   int      not null references users (id),
    receiver_id int      not null references users (id),
    booking_id  int      not null references bookings (id)
);

create table if not exists feedback_users
(
    feedback_id int not null references feedbacks (id),
    user_id     int not null references users (id),
    primary key (feedback_id, user_id)
)