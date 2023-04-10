create table if not exists campaigns
(
    id            int generated always as identity primary key,
    time          timestamp with time zone default now(),
    location      varchar(255),
    cost          int,
    description   text,
    enterprise_id int not null references enterprises (id)
);
