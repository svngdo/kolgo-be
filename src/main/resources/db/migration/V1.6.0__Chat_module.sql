create table if not exists conversations
(
    id          int generated always as identity primary key,
    type        varchar(20) not null
        constraint valid_type check ( type in ('PUBLIC', 'GROUP', 'PRIVATE')),
    user_id     int         not null references users (id),
    receiver_id int         not null
);

create table if not exists user_conversations
(
    conversation_id int not null references conversations (id),
    user_id         int not null references users (id),
    primary key (conversation_id, user_id)
);

create table if not exists messages
(
    id              int generated always as identity primary key,
    type            varchar(20)
        constraint valid_type check ( type in ('JOIN', 'LEAVE', 'MESSAGE')),
    user_id         int  not null references users (id),
    content         text not null,
    timestamp       timestamp with time zone default now(),
    conversation_id int  not null references conversations (id)
);