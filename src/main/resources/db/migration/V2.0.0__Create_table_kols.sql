create table if not exists kols
(
    id            int generated always as identity primary key,
    phone_number  varchar(20),
    speciality    varchar,
    facebook_url  varchar,
    instagram_url varchar,
    tiktok_url    varchar,
    youtube_url   varchar,
    user_id       int      not null references users (id)
);
