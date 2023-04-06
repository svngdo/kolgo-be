create table if not exists campaigns
(
    id            int generated always as identity primary key,
    objective     text not null,
    enterprise_id int  not null references enterprises (id)
);
