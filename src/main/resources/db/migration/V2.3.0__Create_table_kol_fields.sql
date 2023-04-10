create table if not exists kol_fields
(
    id   smallint generated always as identity primary key,
    name varchar(255) not null
)