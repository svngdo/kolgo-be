create table if not exists cities
(
    id           smallint generated always as identity primary key,
    name         varchar(50) not null,
    abbreviation varchar(10)  not null
);