insert into roles(name)
values ('ADMIN'),
       ('USER'),
       ('KOL'),
       ('ENTERPRISE');

insert into users(first_name, last_name, email, password)
values ('admin', 'admin', 'admin@kolgo.com', '$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('sang', 'kol', 'sangkol@kolgo.com', '$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('huy', 'kol', 'huykol@kolgo.com', '$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('thang', 'kol', 'thangkol@kolgo.com', '$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('chan', 'kol', 'chankol@kolgo.com', '$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('hieu', 'kol', 'hieukol@kolgo.com', '$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('sang', 'enterprise', 'sangenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('huy', 'enterprise', 'huyenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('thang', 'enterprise', 'thangenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('chan', 'enterprise', 'chanenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa'),
       ('hieu', 'enterprise', 'hieuenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa');

insert into user_roles(user_id, role_id)
values (1, 1),
       (2, 3),
       (3, 3),
       (4, 3),
       (5, 3),
       (6, 3),
       (7, 4),
       (8, 4),
       (9, 4),
       (10, 4),
       (11, 4);