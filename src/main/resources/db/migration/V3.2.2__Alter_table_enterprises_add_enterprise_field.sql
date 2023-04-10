alter table enterprises
    add column enterprise_field_id smallint null references enterprise_fields on delete set null;