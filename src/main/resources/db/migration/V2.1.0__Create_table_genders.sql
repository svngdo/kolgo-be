create table if not exists genders
(
    id   smallint generated always as identity primary key,
    name varchar(10) not null
)