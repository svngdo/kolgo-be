create table if not exists campaigns
(
    id            int generated always as identity primary key,
    created_at     timestamp with time zone default now(),
    cost          int,
    description   text,
    status        varchar(20) not null
        constraint valid_status check ( status in ('UPCOMING', 'IN_PROGRESS', 'COMPLETED') ),
    enterprise_id int         not null references enterprises (id)
);
