create table if not exists kols
(
    id                        int generated always as identity primary key,
    user_id                   int          not null references users (id),
    gender                    varchar(20)  null
        constraint valid_user_gender check ( gender in ('MALE', 'FEMALE', 'OTHERS')),
    phone                     varchar(20)  null,
    city_id                   int          null references cities (id) on delete set null,
    address_details           varchar(255) null,
    introduction              text         null,
    post_price                numeric      null default 0,
    video_price               numeric      null default 0,
    facebook_url              varchar      null,
    instagram_url             varchar      null,
    tiktok_url                varchar      null,
    youtube_url               varchar      null,
    facebook_followers_count  varchar(20)  null,
    instagram_followers_count varchar(20)  null,
    tiktok_followers_count    varchar(20)  null,
    youtube_subscribers_count varchar(20)  null
);

create table if not exists kol_fields
(
    kol_id   int      not null references kols (id),
    field_id smallint not null references fields (id),
    primary key (kol_id, field_id)
);

create table if not exists kol_images
(
    kol_id   int not null references kols (id) on delete cascade,
    image_id int not null references images (id) on delete cascade,
    primary key (kol_id, image_id)
)