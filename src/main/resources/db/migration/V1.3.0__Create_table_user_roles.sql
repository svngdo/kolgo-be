create table if not exists user_roles
(
    user_id int      not null references users (id),
    role_id smallint not null references roles (id),
    primary key (user_id, role_id)
);