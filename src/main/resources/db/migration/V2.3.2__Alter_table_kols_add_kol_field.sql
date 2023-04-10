alter table kols
    add column kol_field_id smallint null references kol_fields (id) on delete set null;