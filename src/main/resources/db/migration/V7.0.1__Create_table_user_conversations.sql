create table if not exists user_conversations
(
    conversation_id int not null references conversations (id),
    user_id         int not null references users (id),
    primary key (conversation_id, user_id)
)