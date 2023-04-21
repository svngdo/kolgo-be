create table if not exists addresses
(
    id      int generated always as identity primary key,
    details text null,
    city_id smallint null references cities (id) on delete set null
);
