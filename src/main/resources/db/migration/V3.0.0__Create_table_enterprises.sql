create table if not exists enterprises
(
    id                        int generated always as identity primary key,
    name                      varchar(255),
    phone_number              varchar(20),
    tax_identification_number varchar(20),
    user_id                   int not null references users (id)
);
