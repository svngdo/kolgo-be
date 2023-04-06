create table if not exists campaigns_kols
(
    campaign_id int not null references campaigns (id),
    kol_id      int not null references kols (id),
    primary key (campaign_id, kol_id)
);
