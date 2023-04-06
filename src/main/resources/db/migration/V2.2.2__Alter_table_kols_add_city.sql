alter table kols
    add column city_id smallint null references cities (id) on delete set null;
