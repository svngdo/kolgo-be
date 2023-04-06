create table if not exists tokens
(
    id         int generated always as identity primary key,
    value      varchar   not null,
    expires_in timestamp not null default current_timestamp,
    user_id    int       not null references users (id)
);