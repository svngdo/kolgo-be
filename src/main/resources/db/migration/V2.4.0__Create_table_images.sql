create table if not exists images
(
    id     int generated always as identity primary key,
    name   varchar,
    kol_id int not null references kols (id)
);