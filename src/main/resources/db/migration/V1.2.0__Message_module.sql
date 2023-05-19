create table if not exists notifications
(
    id         int generated always as identity primary key,
    type       varchar(20)  not null
        constraint valid_notification_type check ( type in ('NOTIFICATION', 'BOOKING', 'CAMPAIGN')),
    kol_id     int          null,
    booking_id int          null,
    content    varchar(255) not null,
    status     varchar(20)  not null
        constraint valid_notification_status check ( status in ('READ', 'UNREAD') ),
    timestamp  timestamp with time zone default now(),
    user_id    int          not null references users (id)
);

create table if not exists chats
(
    id      int generated always as identity primary key,
    type    varchar(20) not null
        constraint valid_chat_type check ( type in ('PUBLIC', 'GROUP', 'PRIVATE') ),
    date    timestamp with time zone default now(),
    user_id int         not null references users (id)
);

create table if not exists chat_users
(
    chat_id int not null references chats (id),
    user_id int not null references users (id),
    primary key (chat_id, user_id)
);

create table if not exists chat_messages
(
    id        int generated always as identity primary key,
    type      varchar(20) not null
        constraint valid_chat_message_type check ( type in ('JOIN', 'LEAVE', 'CHAT_MESSAGE') ),
    timestamp timestamp with time zone default now(),
    content   text        not null,
    user_id   int         not null references users (id),
    chat_id   int         not null references chats (id)
);