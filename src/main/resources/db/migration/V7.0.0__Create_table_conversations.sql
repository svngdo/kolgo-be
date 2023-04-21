create table if not exists conversations
(
    id          int generated always as identity primary key,
    type        varchar(20) not null
        constraint valid_type check ( type in ('PUBLIC', 'GROUP', 'PRIVATE')),
    user_id     int         not null references users (id),
    receiver_id int         not null
)