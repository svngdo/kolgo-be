create table if not exists enterprise_fields
(
    id   smallint generated always as identity primary key,
    name varchar(255) not null
)
