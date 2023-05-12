create table if not exists cities
(
    id   smallint generated always as identity primary key,
    name varchar(50) not null,
    code varchar(10) not null
);

create table if not exists addresses
(
    id      int generated always as identity primary key,
    city_id smallint     null references cities (id) on delete set null,
    details varchar(255) null
);

create table if not exists fields
(
    id   smallint generated always as identity primary key,
    name varchar(255) not null,
    type varchar(10)  not null
        constraint valid_field_type check ( type in ('KOL', 'ENTERPRISE'))
);

create table if not exists kols
(
    id            int generated always as identity primary key,
    user_id       int         not null references users (id),
    gender        varchar(20) null
        constraint valid_user_gender check ( gender in ('MALE', 'FEMALE', 'OTHERS')),
    phone         varchar(20) null,
    address_id    int         null references addresses (id),
    post_price    numeric     not null default 0,
    video_price   numeric     not null default 0,
    facebook_url  varchar,
    instagram_url varchar,
    tiktok_url    varchar,
    youtube_url   varchar
);

create table if not exists kol_fields
(
    kol_id   int      not null references kols (id),
    field_id smallint not null references fields (id),
    primary key (kol_id, field_id)
);

create table if not exists images
(
    id     int generated always as identity primary key,
    name   varchar,
    kol_id int not null references kols (id)
);