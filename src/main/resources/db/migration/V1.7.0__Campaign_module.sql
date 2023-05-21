create table if not exists campaigns
(
    id            int generated always as identity primary key,
    name          varchar(255) not null,
    timestamp     timestamp with time zone default now(),
    start_time    timestamp with time zone default now(),
    finish_time   timestamp with time zone default now(),
    location      varchar(255) not null,
    description   text         null,
    details       text         null,
    status        varchar(20)  not null
        constraint valid_status check ( status in ('UPCOMING', 'IN_PROGRESS', 'COMPLETED') ),
    enterprise_id int          not null references enterprises (id)
);

create table if not exists campaign_fields
(
    campaign_id int not null references campaigns (id),
    field_id    int not null references fields (id),
    primary key (campaign_id, field_id)
);

create table if not exists campaign_images
(
    campaign_id int not null references campaigns (id),
    image_id    int not null references images (id),
    primary key (campaign_id, image_id)
);

create table if not exists campaign_kols
(
    campaign_id int not null references campaigns (id),
    kol_id      int not null references kols (id),
    primary key (campaign_id, kol_id)
);