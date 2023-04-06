alter table kols
    add gender_id smallint null references genders (id) on delete set null;
