alter table enterprises
    add column address_id int null references addresses (id) on delete set null;
