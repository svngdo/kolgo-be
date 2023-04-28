create table if not exists genders
(
    id   smallint generated always as identity primary key,
    name varchar(10) not null
);

create table if not exists cities
(
    id           smallint generated always as identity primary key,
    name         varchar(50) not null,
    abbreviation varchar(10) not null
);

create table if not exists kol_fields
(
    id   smallint generated always as identity primary key,
    name varchar(255) not null
);

create table if not exists kols
(
    id            int generated always as identity primary key,
    facebook_url  varchar,
    instagram_url varchar,
    tiktok_url    varchar,
    youtube_url   varchar,
    gender_id     smallint null references genders (id) on delete set null,
    city_id       smallint null references cities (id) on delete set null,
    kol_field_id  smallint null references kol_fields (id) on delete set null,
    user_id       int      not null references users (id)
);

create table if not exists images
(
    id     int generated always as identity primary key,
    name   varchar,
    kol_id int not null references kols (id)
);